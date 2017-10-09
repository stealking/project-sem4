
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'user', 'user', 'enable@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', false, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user1', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test1@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user2', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test2@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user3', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test3@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user4', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test4@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user5', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test5@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user6', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test6@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user7', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test7@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user8', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test8@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user9', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test9@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO users (username, "password", first_name, last_name, email, enable, last_password_reset_date) VALUES ( 'user10', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'test10@user.com', true, to_date('01-01-2016', 'dd-MM-yyyy'));


INSERT INTO Authority (id, name) VALUES (1, 'ROLE_STAFF');
INSERT INTO Authority (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO Authority (id, name) VALUES (3, 'ROLE_CLIENT');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 1);


INSERT INTO tour_type (name) VALUES ( 'Khách lẻ');
INSERT INTO tour_type (name) VALUES ( 'Khách theo đoàn');

INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Hà Nội', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 2, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'Nha Trang', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 2, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'Vũng Tàu', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 2, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Hà Nội', 'Cần Thơ', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM - Cần Thơ', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'Cần Thơ', 'Details', true, '', '12345', 'Hà Nội - Đà Nẵng - Nha Trang - Cần Thơ', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Đà Nẵng', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 2, null);
INSERT INTO tour (department_point, destination, detail, enable, image, introduction, journey, total_time, transport, updated_on, tour_type_id, updated_by) VALUES ('Hà Nội', 'HCM', 'Details', true, '', '12345', 'Đà Nẵng - Nha Trang - HCM', '4 ngày 3 đêm', 'Xe ô tô', null, 1, null);

INSERT INTO voucher(content, discount_percent, enable, expired_on, image, introduction, updated_on, updated_by) VALUES ('GIảm Giá 10%', '10', true, '2017-09-04 00:00:00', null, null, '2017-09-04 00:00:00', 1);
INSERT INTO voucher(content, discount_percent, enable, expired_on, image, introduction, updated_on, updated_by) VALUES ('GIảm Giá 20%', '20', true, '2017-09-04 00:00:00', null, null, '2017-09-04 00:00:00', 1);
INSERT INTO voucher(content, discount_percent, enable, expired_on, image, introduction, updated_on, updated_by) VALUES ('GIảm Giá 30%', '30', true, '2017-09-04 00:00:00', null, null, '2017-09-04 00:00:00', 1);

INSERT INTO tour_detail(department_date, details, enable, ex_cost, price_adult, price_baby, price_children, updated_on, tour_id, updated_by, voucher_id) VALUES ('2017-09-04 00:00:00','Tour thang 7',true,'55555555','666666666666','7777777','88888888888','2017-09-04 00:00:00','1','1', 1);
INSERT INTO tour_detail(department_date, details, enable, ex_cost, price_adult, price_baby, price_children, updated_on, tour_id, updated_by, voucher_id) VALUES ('2017-09-04 00:00:00','Tour thang 8',true,'55555555','666666666666','7777777','88888888888','2017-09-04 00:00:00','1','1', null);
INSERT INTO tour_detail(department_date, details, enable, ex_cost, price_adult, price_baby, price_children, updated_on, tour_id, updated_by, voucher_id) VALUES ('2017-09-04 00:00:00','Tour thang 9',true,'55555555','666666666666','7777777','88888888888','2017-09-04 00:00:00','1','1', null);
