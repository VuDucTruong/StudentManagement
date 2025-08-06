-- ============================================
-- Reset all tables
-- ============================================
TRUNCATE TABLE
    account,
    class_section,
    enrollment,
    faculty,
    major,
    payment,
    prerequisite,
    program,
    schedule,
    score,
    semester,
    student,
    student_class,
    subject,
    teacher,
    tuition,
    tuition_fee
    RESTART IDENTITY CASCADE;

-- ============================================
-- Faculty
-- ============================================
INSERT INTO public.faculty (id, dean_id, name, deleted_at, updated_at)
VALUES (1, NULL, 'Khoa Công nghệ Thông tin', NULL, NULL),
       (2, NULL, 'Khoa Hệ thống Thông tin', NULL, NULL),
       (3, NULL, 'Khoa Khoa học Máy tính', NULL, NULL),
       (4, NULL, 'Khoa An toàn Thông tin', NULL, NULL),
       (5, NULL, 'Khoa Truyền thông và Mạng máy tính', NULL, NULL),
       (6, NULL, 'Khoa Công nghệ Phần mềm', NULL, NULL),
       (7, NULL, 'Khoa Trí tuệ Nhân tạo', NULL, NULL),
       (8, NULL, 'Khoa Kỹ thuật Máy tính', NULL, NULL),
       (9, NULL, 'Khoa Khoa học Dữ liệu', NULL, NULL),
       (10, NULL, 'Khoa Toán - Tin học', NULL, NULL);

-- ============================================
-- Teacher
-- ============================================
INSERT INTO public.teacher (id, faculty_id, name, degree, specialization, dob, hire_date, email,
                            phone, deleted_at, updated_at)
VALUES (1, 1, 'Nguyễn Văn An', 'Tiến sĩ', 'Khoa học máy tính', '1980-05-12', '2010-09-01',
        'an.nguyen@uit.edu.vn', '0901234567', NULL, NULL),
       (2, 2, 'Trần Thị Bình', 'Thạc sĩ', 'Hệ thống thông tin', '1985-03-20', '2012-08-15',
        'binh.tran@uit.edu.vn', '0902234567', NULL, NULL),
       (3, 3, 'Phạm Quốc Cường', 'Tiến sĩ', 'Trí tuệ nhân tạo', '1978-12-01', '2008-02-20',
        'cuong.pham@uit.edu.vn', '0903234567', NULL, NULL),
       (4, 4, 'Lê Hoàng Dũng', 'Thạc sĩ', 'An toàn thông tin', '1982-07-07', '2011-05-10',
        'dung.le@uit.edu.vn', '0904234567', NULL, NULL),
       (5, 5, 'Vũ Thị Hà', 'Tiến sĩ', 'Mạng máy tính', '1984-10-14', '2013-01-05',
        'ha.vu@uit.edu.vn', '0905234567', NULL, NULL),
       (6, 6, 'Hoàng Văn Nam', 'Thạc sĩ', 'Công nghệ phần mềm', '1983-09-22', '2011-09-01',
        'nam.hoang@uit.edu.vn', '0906234567', NULL, NULL),
       (7, 7, 'Ngô Minh Quân', 'Tiến sĩ', 'Trí tuệ nhân tạo', '1979-06-18', '2009-04-03',
        'quan.ngo@uit.edu.vn', '0907234567', NULL, NULL),
       (8, 8, 'Đặng Thị Lan', 'Thạc sĩ', 'Kỹ thuật máy tính', '1986-02-11', '2014-03-15',
        'lan.dang@uit.edu.vn', '0908234567', NULL, NULL),
       (9, 9, 'Bùi Thanh Sơn', 'Tiến sĩ', 'Khoa học dữ liệu', '1981-01-25', '2010-06-22',
        'son.bui@uit.edu.vn', '0909234567', NULL, NULL),
       (10, 10, 'Nguyễn Thị Thu', 'Thạc sĩ', 'Toán - Tin học', '1987-11-09', '2015-08-12',
        'thu.nguyen@uit.edu.vn', '0910234567', NULL, NULL);

-- Set dean_id for faculties
UPDATE public.faculty
SET dean_id = 1
WHERE id = 1;
UPDATE public.faculty
SET dean_id = 2
WHERE id = 2;
UPDATE public.faculty
SET dean_id = 3
WHERE id = 3;
UPDATE public.faculty
SET dean_id = 4
WHERE id = 4;
UPDATE public.faculty
SET dean_id = 5
WHERE id = 5;
UPDATE public.faculty
SET dean_id = 6
WHERE id = 6;
UPDATE public.faculty
SET dean_id = 7
WHERE id = 7;
UPDATE public.faculty
SET dean_id = 8
WHERE id = 8;
UPDATE public.faculty
SET dean_id = 9
WHERE id = 9;
UPDATE public.faculty
SET dean_id = 10
WHERE id = 10;

-- ============================================
-- Major
-- ============================================
INSERT INTO public.major (id, faculty_id, name, deleted_at, updated_at)
VALUES (1, 1, 'Kỹ thuật phần mềm', NULL, NULL),
       (2, 1, 'Công nghệ thông tin', NULL, NULL),
       (3, 2, 'Hệ thống thông tin quản lý', NULL, NULL),
       (4, 3, 'Khoa học máy tính', NULL, NULL),
       (5, 4, 'An toàn thông tin', NULL, NULL),
       (6, 5, 'Truyền thông và mạng máy tính', NULL, NULL),
       (7, 6, 'Công nghệ phần mềm tiên tiến', NULL, NULL),
       (8, 7, 'Trí tuệ nhân tạo', NULL, NULL),
       (9, 8, 'Kỹ thuật máy tính', NULL, NULL),
       (10, 9, 'Khoa học dữ liệu', NULL, NULL);

-- ============================================
-- Program
-- ============================================
INSERT INTO public.program (id, major_id, name, duration, level, deleted_at, updated_at)
VALUES (1, 1, 'Chương trình Đại trà', 4, 0, NULL, NULL),
       (2, 1, 'Chương trình Chất lượng cao', 4, 1, NULL, NULL),
       (3, 2, 'Chương trình Đại trà', 4, 0, NULL, NULL),
       (4, 3, 'Chương trình Đại trà', 4, 0, NULL, NULL),
       (5, 4, 'Chương trình Chất lượng cao', 4, 1, NULL, NULL),
       (6, 5, 'Chương trình Đại trà', 4, 0, NULL, NULL),
       (7, 6, 'Chương trình Đại trà', 4, 0, NULL, NULL),
       (8, 7, 'Chương trình Chất lượng cao', 4, 1, NULL, NULL),
       (9, 8, 'Chương trình Đại trà', 4, 0, NULL, NULL),
       (10, 9, 'Chương trình Chất lượng cao', 4, 1, NULL, NULL);

-- ============================================
-- Semester
-- ============================================
INSERT INTO public.semester (id, name, start_date, end_date, deleted_at, updated_at)
VALUES (1, 'Học kỳ 1 - Năm học 2023-2024', '2023-09-01', '2024-01-15', NULL, NULL),
       (2, 'Học kỳ 2 - Năm học 2023-2024', '2024-02-01', '2024-06-15', NULL, NULL),
       (3, 'Học kỳ hè - Năm học 2023-2024', '2024-07-01', '2024-08-15', NULL, NULL),
       (4, 'Học kỳ 1 - Năm học 2024-2025', '2024-09-01', '2025-01-15', NULL, NULL),
       (5, 'Học kỳ 2 - Năm học 2024-2025', '2025-02-01', '2025-06-15', NULL, NULL),
       (6, 'Học kỳ hè - Năm học 2024-2025', '2025-07-01', '2025-08-15', NULL, NULL),
       (7, 'Học kỳ 1 - Năm học 2022-2023', '2022-09-01', '2023-01-15', NULL, NULL),
       (8, 'Học kỳ 2 - Năm học 2022-2023', '2023-02-01', '2023-06-15', NULL, NULL),
       (9, 'Học kỳ hè - Năm học 2022-2023', '2023-07-01', '2023-08-15', NULL, NULL),
       (10, 'Học kỳ đặc biệt', '2025-01-20', '2025-02-15', NULL, NULL);

-- ============================================
-- Student Class
-- ============================================
INSERT INTO public.student_class (id, name, start_year, end_year, deleted_at, updated_at)
VALUES (1, 'CNTT2022A', 2022, 2026, NULL, NULL),
       (2, 'CNTT2022B', 2022, 2026, NULL, NULL),
       (3, 'HTTT2023A', 2023, 2027, NULL, NULL),
       (4, 'HTTT2023B', 2023, 2027, NULL, NULL),
       (5, 'KTPM2021A', 2021, 2025, NULL, NULL),
       (6, 'KTPM2021B', 2021, 2025, NULL, NULL),
       (7, 'ATTT2023A', 2023, 2027, NULL, NULL),
       (8, 'AI2022A', 2022, 2026, NULL, NULL),
       (9, 'KHMT2022A', 2022, 2026, NULL, NULL),
       (10, 'TTM2023A', 2023, 2027, NULL, NULL);

-- ============================================
-- Tuition Fee
-- ============================================
INSERT INTO public.tuition_fee (id, program_id, academic_year, effective_date, expiry_date,
                                credit_fee, fixed_fee, deleted_at, updated_at)
VALUES (1, 1, 2024, '2024-09-01', '2025-08-31', 350000, 5000000, NULL, NULL),
       (2, 2, 2024, '2024-09-01', '2025-08-31', 550000, 8000000, NULL, NULL),
       (3, 3, 2024, '2024-09-01', '2025-08-31', 350000, 5000000, NULL, NULL),
       (4, 4, 2024, '2024-09-01', '2025-08-31', 350000, 5000000, NULL, NULL),
       (5, 5, 2024, '2024-09-01', '2025-08-31', 550000, 8000000, NULL, NULL),
       (6, 6, 2024, '2024-09-01', '2025-08-31', 350000, 5000000, NULL, NULL),
       (7, 7, 2024, '2024-09-01', '2025-08-31', 350000, 5000000, NULL, NULL),
       (8, 8, 2024, '2024-09-01', '2025-08-31', 550000, 8000000, NULL, NULL),
       (9, 9, 2024, '2024-09-01', '2025-08-31', 350000, 5000000, NULL, NULL),
       (10, 10, 2024, '2024-09-01', '2025-08-31', 550000, 8000000, NULL, NULL);

-- ============================================
-- Student
-- ============================================
INSERT INTO public.student (id, major_id, class_id, fee_id, name, gender, dob, entry_date, status,
                            address, email, phone, deleted_at, updated_at)
VALUES (1, 1, 1, 1, 'Nguyễn Minh Hoàng', 0, '2004-03-12', '2022-09-01', 1, 'TP. Hồ Chí Minh',
        'hoang.nm2022@uit.edu.vn', '0901111111', NULL, NULL),
       (2, 2, 2, 2, 'Trần Thị Mai', 1, '2004-07-18', '2022-09-01', 1, 'TP. Hồ Chí Minh',
        'mai.tt2022@uit.edu.vn', '0901111112', NULL, NULL),
       (3, 3, 3, 3, 'Lê Văn Nam', 0, '2005-01-05', '2023-09-01', 1, 'Bình Dương',
        'nam.lv2023@uit.edu.vn', '0901111113', NULL, NULL),
       (4, 4, 4, 4, 'Phạm Ngọc Lan', 1, '2005-02-14', '2023-09-01', 1, 'TP. Hồ Chí Minh',
        'lan.pn2023@uit.edu.vn', '0901111114', NULL, NULL),
       (5, 5, 5, 5, 'Võ Thanh Tùng', 0, '2003-11-20', '2021-09-01', 2, 'Đồng Nai',
        'tung.vt2021@uit.edu.vn', '0901111115', NULL, NULL),
       (6, 6, 6, 6, 'Bùi Thị Hồng', 1, '2003-05-09', '2021-09-01', 1, 'TP. Hồ Chí Minh',
        'hong.bt2021@uit.edu.vn', '0901111116', NULL, NULL),
       (7, 7, 7, 7, 'Đinh Công Minh', 0, '2005-04-28', '2023-09-01', 1, 'Long An',
        'minh.dc2023@uit.edu.vn', '0901111117', NULL, NULL),
       (8, 8, 8, 8, 'Ngô Thị Kim', 1, '2004-06-30', '2022-09-01', 0, 'TP. Hồ Chí Minh',
        'kim.nt2022@uit.edu.vn', '0901111118', NULL, NULL),
       (9, 9, 9, 9, 'Hồ Văn Hải', 0, '2004-09-22', '2022-09-01', 1, 'TP. Hồ Chí Minh',
        'hai.hv2022@uit.edu.vn', '0901111119', NULL, NULL),
       (10, 10, 10, 10, 'Phan Ngọc Yến', 1, '2005-12-01', '2023-09-01', 1, 'TP. Hồ Chí Minh',
        'yen.pn2023@uit.edu.vn', '0901111120', NULL, NULL);
-- ============================================
-- Subject
-- ============================================
INSERT INTO public.subject (id, major_id, name, subject_code, credits, description, deleted_at,
                            updated_at)
VALUES (1, 1, 'Lập trình Java', 'IT101', 3, 'Học lập trình Java cơ bản và nâng cao', NULL, NULL),
       (2, 1, 'Cơ sở dữ liệu', 'IT102', 3, 'Giới thiệu về hệ quản trị cơ sở dữ liệu', NULL, NULL),
       (3, 2, 'Mạng máy tính', 'IT201', 3, 'Nguyên lý và ứng dụng của mạng máy tính', NULL, NULL),
       (4, 3, 'Hệ thống thông tin quản lý', 'IS301', 3, 'Phân tích và thiết kế hệ thống thông tin',
        NULL, NULL),
       (5, 4, 'Trí tuệ nhân tạo', 'CS401', 3, 'Nguyên lý AI và các ứng dụng', NULL, NULL),
       (6, 5, 'An ninh mạng', 'SE501', 3, 'Nguyên tắc bảo mật hệ thống', NULL, NULL),
       (7, 6, 'Công nghệ phần mềm', 'SE601', 3, 'Kỹ thuật và công cụ phát triển phần mềm', NULL,
        NULL),
       (8, 7, 'Machine Learning', 'AI701', 3, 'Thuật toán học máy và ứng dụng', NULL, NULL),
       (9, 8, 'Kiến trúc máy tính', 'CE801', 3, 'Cấu trúc và tổ chức phần cứng', NULL, NULL),
       (10, 9, 'Phân tích dữ liệu', 'DS901', 3, 'Khai thác và phân tích dữ liệu', NULL, NULL);

-- ============================================
-- Class Section
-- ============================================
INSERT INTO public.class_section (id, semester_id, subject_id, teacher_id, name, start_date,
                                  end_date, deleted_at, updated_at)
VALUES (1, 4, 1, 1, 'Lập trình Java - L01', '2024-09-05', '2025-01-10', NULL, NULL),
       (2, 4, 2, 2, 'Cơ sở dữ liệu - L01', '2024-09-06', '2025-01-11', NULL, NULL),
       (3, 4, 3, 3, 'Mạng máy tính - L01', '2024-09-07', '2025-01-12', NULL, NULL),
       (4, 4, 4, 4, 'Hệ thống thông tin - L01', '2024-09-08', '2025-01-13', NULL, NULL),
       (5, 4, 5, 5, 'Trí tuệ nhân tạo - L01', '2024-09-09', '2025-01-14', NULL, NULL),
       (6, 4, 6, 6, 'An ninh mạng - L01', '2024-09-10', '2025-01-15', NULL, NULL),
       (7, 4, 7, 7, 'Công nghệ phần mềm - L01', '2024-09-11', '2025-01-16', NULL, NULL),
       (8, 4, 8, 8, 'Machine Learning - L01', '2024-09-12', '2025-01-17', NULL, NULL),
       (9, 4, 9, 9, 'Kiến trúc máy tính - L01', '2024-09-13', '2025-01-18', NULL, NULL),
       (10, 4, 10, 10, 'Phân tích dữ liệu - L01', '2024-09-14', '2025-01-19', NULL, NULL);

-- ============================================
-- Enrollment
-- ============================================
INSERT INTO public.enrollment (id, section_id, student_id, status, deleted_at, updated_at)
VALUES (1, 1, 1, 1, NULL, NULL),
       (2, 2, 2, 1, NULL, NULL),
       (3, 3, 3, 2, NULL, NULL),
       (4, 4, 4, 1, NULL, NULL),
       (5, 5, 5, 0, NULL, NULL),
       (6, 6, 6, 2, NULL, NULL),
       (7, 7, 7, 1, NULL, NULL),
       (8, 8, 8, 1, NULL, NULL),
       (9, 9, 9, 2, NULL, NULL),
       (10, 10, 10, 1, NULL, NULL);

-- ============================================
-- Score
-- ============================================
INSERT INTO public.score (id, enrollment_id, process_score, mid_term_score, final_score, deleted_at,
                          updated_at)
VALUES (1, 1, 8.0, 7.5, 8.5, NULL, NULL),
       (2, 2, 7.0, 6.5, 7.5, NULL, NULL),
       (3, 3, 8.5, 8.0, 9.0, NULL, NULL),
       (4, 4, 7.5, 7.0, 8.0, NULL, NULL),
       (5, 5, NULL, NULL, NULL, NULL, NULL),
       (6, 6, 6.5, 6.0, 6.5, NULL, NULL),
       (7, 7, 7.0, 7.0, 7.5, NULL, NULL),
       (8, 8, 8.0, 8.0, 8.5, NULL, NULL),
       (9, 9, 9.0, 8.5, 9.5, NULL, NULL),
       (10, 10, 7.5, 7.0, 7.5, NULL, NULL);

-- ============================================
-- Tuition
-- ============================================
INSERT INTO public.tuition (id, semester_id, student_id, total_amount, remaining_amount, status,
                            deleted_at, updated_at)
VALUES (1, 4, 1, 20000000, 0, 1, NULL, NULL),
       (2, 4, 2, 22000000, 0, 1, NULL, NULL),
       (3, 4, 3, 21000000, 5000000, 2, NULL, NULL),
       (4, 4, 4, 23000000, 0, 1, NULL, NULL),
       (5, 4, 5, 20000000, 20000000, 0, NULL, NULL),
       (6, 4, 6, 22000000, 0, 1, NULL, NULL),
       (7, 4, 7, 21000000, 3000000, 2, NULL, NULL),
       (8, 4, 8, 23000000, 23000000, 0, NULL, NULL),
       (9, 4, 9, 20000000, 0, 1, NULL, NULL),
       (10, 4, 10, 22000000, 0, 1, NULL, NULL);

-- ============================================
-- Payment
-- ============================================
INSERT INTO public.payment (id, tuition_id, payment_date, payment_method, amount_paid, note,
                            reference_number, deleted_at, updated_at)
VALUES (1, 1, '2024-09-10', 0, 20000000, 'Thanh toán học phí HK1', 'PMT001', NULL, NULL),
       (2, 2, '2024-09-11', 1, 22000000, 'Thanh toán học phí HK1', 'PMT002', NULL, NULL),
       (3, 3, '2024-09-12', 0, 16000000, 'Thanh toán lần 1', 'PMT003', NULL, NULL),
       (4, 4, '2024-09-13', 0, 23000000, 'Thanh toán học phí HK1', 'PMT004', NULL, NULL),
       (5, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (6, 6, '2024-09-14', 1, 22000000, 'Thanh toán học phí HK1', 'PMT006', NULL, NULL),
       (7, 7, '2024-09-15', 0, 18000000, 'Thanh toán lần 1', 'PMT007', NULL, NULL),
       (8, 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       (9, 9, '2024-09-16', 1, 20000000, 'Thanh toán học phí HK1', 'PMT009', NULL, NULL),
       (10, 10, '2024-09-17', 0, 22000000, 'Thanh toán học phí HK1', 'PMT010', NULL, NULL);

-- ============================================
-- Schedule
-- ============================================
INSERT INTO public.schedule (id, section_id, week_day, start_period, end_period, room, deleted_at,
                             updated_at)
VALUES (1, 1, 0, 1, 3, 'A101', NULL, NULL),
       (2, 2, 1, 4, 6, 'B202', NULL, NULL),
       (3, 3, 2, 1, 3, 'C303', NULL, NULL),
       (4, 4, 3, 4, 6, 'D404', NULL, NULL),
       (5, 5, 4, 1, 3, 'E505', NULL, NULL),
       (6, 6, 0, 4, 6, 'F606', NULL, NULL),
       (7, 7, 1, 1, 3, 'G707', NULL, NULL),
       (8, 8, 2, 4, 6, 'H808', NULL, NULL),
       (9, 9, 3, 1, 3, 'I909', NULL, NULL),
       (10, 10, 4, 4, 6, 'J101', NULL, NULL);

-- ============================================
-- Prerequisite
-- ============================================
INSERT INTO public.prerequisite (id, subject_id, prerequisite_id, pass_requirement, deleted_at,
                                 updated_at)
VALUES (1, 2, 1, true, NULL, NULL),
       (2, 3, 2, true, NULL, NULL),
       (3, 4, 3, true, NULL, NULL),
       (4, 5, 4, true, NULL, NULL),
       (5, 6, 3, true, NULL, NULL),
       (6, 7, 2, true, NULL, NULL),
       (7, 8, 5, true, NULL, NULL),
       (8, 9, 3, true, NULL, NULL),
       (9, 10, 2, true, NULL, NULL),
       (10, 5, 1, false, NULL, NULL);

-- ============================================
-- Account
-- ============================================
INSERT INTO public.account (id, linked_id, username, password, roles, avatar_url, deleted_at,
                            updated_at)
VALUES (2, NULL, 'admin2', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{2}',
        NULL, NULL, NULL),
       (3, 1, 'hoangnm', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{0}',
        NULL, NULL, NULL),
       (4, 2, 'maitt', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{0}', NULL,
        NULL, NULL),
       (5, 3, 'namlv', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{0}', NULL,
        NULL, NULL),
       (6, 4, 'lanpn', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{0}', NULL,
        NULL, NULL),
       (7, 5, 'tungvt', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{0}', NULL,
        NULL, NULL),
       (8, 6, 'hongbt', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{0}', NULL,
        NULL, NULL),
       (9, 7, 'minhdc', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{0}', NULL,
        NULL, NULL),
       (10, 1, 'an.nguyen', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{1}',
        NULL, NULL, NULL),
       (11, 2, 'binh.tran', '$2a$10$DQHb/Nsz5hHtk5TDSNAOROysSDgRSUcwzdcd01aV0AJge0dfnsPEW', '{1}',
        NULL, NULL, NULL);
