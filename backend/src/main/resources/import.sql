
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


INSERT INTO tour_type (name, enable) VALUES ( 'Khách lẻ', true);
INSERT INTO tour_type (name, enable) VALUES ( 'Khách theo đoàn', true);

INSERT INTO journey (name, enable) VALUES ( 'Đà Nẵng - Huế', true);
INSERT INTO journey (name, enable) VALUES ( 'Cà Mau - Long An - Tiền Giang', true);
INSERT INTO journey (name, enable) VALUES ( 'Hồ Chí Minh - Cà Mau - Phú Quốc', true);
INSERT INTO journey (name, enable) VALUES ( 'Cà Mau - Phú Quốc', true);

INSERT INTO departure (name, enable) VALUES ( 'Đà Nẵng', true);
INSERT INTO departure (name, enable) VALUES ( 'Cà Mau', true);
INSERT INTO departure (name, enable) VALUES ( 'Hồ Chí Minh', true);
INSERT INTO departure (name, enable) VALUES ( 'Hà Nội', true);


INSERT INTO transport (name, enable) VALUES ( 'Máy bay J', true);
INSERT INTO transport (name, enable) VALUES ( 'Xe ô tô', true);
INSERT INTO transport (name, enable) VALUES ( 'Tàu Hỏa', true);
INSERT INTO transport (name, enable) VALUES ( 'Máy bay Airline', true);

INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (1, 3, 1, 'Details', true, '', '12345', 4.5,  null, 1, null, 6000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (1, 2, 2, 'Details', true, '', '12345', 3.5,  null, 1, null, 5000000, true);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (1, 1, 3, 'Details', true, '', '12345', 5.5,  null, 1, null, 2000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (2, 3, 4, 'Details', true, '', '12345', 7.5,  null, 2, null, 7000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (2, 2, 1, 'Details', true, '', '12345', 5.5,  null, 1, null, 6000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (2, 1, 2, 'Details', true, '', '12345', 4.5,  null, 1, null, 5000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (1, 3, 3, 'Details', true, '', '12345', 8.5,  null, 2, null, 4000000, true);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (1, 2, 4, 'Details', true, '', '12345', 6.5,  null, 1, null, 8000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (1, 1, 1, 'Details', true, '', '12345', 5.5,  null, 1, null, 7000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (2, 3, 2, 'Details', true, '', '12345', 2.5,  null, 1, null, 3000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (2, 2, 3, 'Details', true, '', '12345', 5.5,  null, 1, null, 4000000, true);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (2, 1, 4, 'Details', true, '', '12345', 6.5,  null, 2, null, 5000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (1, 3, 1, 'Details', true, '', '12345', 7.5,  null, 1, null, 7000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (1, 2, 2, 'Details', true, '', '12345', 8.5,  null, 1, null, 3000000, true);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (1, 1, 3, 'Details', true, '', '12345', 9.5,  null, 1, null, 5000000, false);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (2, 3, 4, 'Details', true, '', '12345', 7.5,  null, 1, null, 3000000, true);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (2, 2, 1, 'Details', true, '', '12345', 6.5,  null, 2, null, 2000000, true);
INSERT INTO tour ( journey_id, departure_id, transport_id, detail, enable, image, introduction,  total_time, updated_on, tour_type_id, updated_by, price, sale_off) VALUES (2, 1, 2, 'Details', true, '', '12345', 6.5,  null, 1, null, 1000000, false);

INSERT INTO voucher(content, discount_percent, enable, expired_on, image, introduction, updated_on, updated_by) VALUES ('Giảm Giá 10%', '10', true, '2017-09-04 00:00:00', null, null, '2017-09-04 00:00:00', 1);
INSERT INTO voucher(content, discount_percent, enable, expired_on, image, introduction, updated_on, updated_by) VALUES ('Giảm Giá 20%', '20', true, '2017-09-04 00:00:00', null, null, '2017-09-04 00:00:00', 1);
INSERT INTO voucher(content, discount_percent, enable, expired_on, image, introduction, updated_on, updated_by) VALUES ('Giảm Giá 30%', '30', true, '2017-09-04 00:00:00', null, null, '2017-09-04 00:00:00', 1);

INSERT INTO tour_detail(department_date, details, enable, ex_cost, price_adult, price_baby, price_children, updated_on, tour_id, voucher_id) VALUES ('2017-10-24 00:00:00','Tour thang 7',true,'55555555','666666666666','7777777','88888888888','2017-09-04 00:00:00',1, 1);
INSERT INTO tour_detail(department_date, details, enable, ex_cost, price_adult, price_baby, price_children, updated_on, tour_id, voucher_id) VALUES ('2017-09-04 00:00:00','Tour thang 8',true,'55555555','666666666666','7777777','88888888888','2017-09-04 00:00:00',2, null);
INSERT INTO tour_detail(department_date, details, enable, ex_cost, price_adult, price_baby, price_children, updated_on, tour_id, voucher_id) VALUES ('2017-09-04 00:00:00','Tour thang 9',true,'55555555','666666666666','7777777','88888888888','2017-09-04 00:00:00',3, null);

INSERT INTO tour_register(tour_detail_id, representative_id, number_adult, number_children, number_baby, registered_on, enable) VALUES (1, 1, 2, 3, 4,'2017-09-04 00:00:00', true);
INSERT INTO tour_register(tour_detail_id, representative_id, number_adult, number_children, number_baby, registered_on, enable) VALUES (2, 1, 2, 3, 4,'2017-09-04 00:00:00', true);
INSERT INTO tour_register(tour_detail_id, representative_id, number_adult, number_children, number_baby, registered_on, enable) VALUES (3, 1, 2, 3, 4,'2017-09-04 00:00:00', true);
