-- flyway:executeInTransaction=false

-- Tạo function cho trigger set updated at
CREATE OR REPLACE FUNCTION set_updated_at()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at := CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DO $$
    DECLARE
        tbl TEXT;
    BEGIN
        FOR tbl IN
            SELECT table_name
            FROM information_schema.tables
            WHERE table_schema = 'public'
            LOOP
                EXECUTE format(
                        'CREATE TRIGGER trigger_set_updated_at_%s
                         BEFORE UPDATE OR INSERT ON %I
                         FOR EACH ROW
                         EXECUTE FUNCTION set_updated_at();',
                        tbl, tbl
                        );
            END LOOP;
    END $$;


-- Tạo hoặc thay thế function cập nhật tuition
CREATE OR REPLACE FUNCTION update_tuition_after_payment()
    RETURNS TRIGGER AS $$
BEGIN
    -- Cập nhật remaining_amount và status
    UPDATE tuition
    SET remaining_amount = remaining_amount - NEW.amount_paid,
        status = CASE
                     WHEN remaining_amount - NEW.amount_paid <= 0 THEN 1 -- PAID
                     ELSE status
            END
    WHERE id = NEW.tuition_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Xóa trigger nếu tồn tại (tránh lỗi khi chạy lại migration)
DROP TRIGGER IF EXISTS trg_update_tuition_after_payment ON payment;

-- Tạo trigger: chạy sau khi insert payment
CREATE TRIGGER trg_update_tuition_after_payment
    AFTER INSERT ON payment
    FOR EACH ROW
EXECUTE FUNCTION update_tuition_after_payment();