--insert into user_bean(id, email, first_name, last_name, password, profile_picture, username) 
--values (,'','','','','','');

insert into user_bean(id, email, first_name, last_name, password, profile_picture, username) 
values (1,'mail1@gmail.com','Some','1','password1','NA','UserName1');

insert into user_bean(id, email, first_name, last_name, password, profile_picture, username) 
values (2,'mail2@gmail.com','Some','2','password2','NA','UserName2');

insert into user_bean(id, email, first_name, last_name, password, profile_picture, username) 
values (3,'mail3@gmail.com','Some','3','password3','NA','UserName3');

insert into user_bean(id, email, first_name, last_name, password, profile_picture, username) 
values (4,'mail4@gmail.com','Some','4','password4','NA','UserName4');

insert into user_bean(id, email, first_name, last_name, password, profile_picture, username) 
values (5,'mail5@gmail.com','Some','5','password5','NA','UserName5');

--insert into restaurant_bean(id, name, type) values (, '', '');

insert into restaurant_bean(id, name, type) values (1, 'BurgerKing', 'Fast Food');
insert into restaurant_bean(id, name, type) values (2, 'McDonalds', 'Fast Food');
insert into restaurant_bean(id, name, type) values (3, 'Dva stapica', 'Chinese');
insert into restaurant_bean(id, name, type) values (4, 'Hells Kitchen', 'Fine Dining');
insert into restaurant_bean(id, name, type) values (5, 'Italian Restaurant', 'Italian Cuisine');
insert into restaurant_bean(id, name, type) values (6, 'Japanese Restaurant', 'Sushi Japanese');

--insert into product_bean(id,description,name,price) values (, , , )

insert into product_bean(id,description,name,price) values (1, 'Spicy', 'Burger', 100);
insert into product_bean(id,description,name,price) values (2, 'Raw', 'Sushi', 200);
insert into product_bean(id,description,name,price) values (3, 'Cold', 'Ice-Cream', 300);
insert into product_bean(id,description,name,price) values (4, 'Red', 'Vine', 300);
insert into product_bean(id,description,name,price) values (5, 'Cold', 'Cola', 200);
insert into product_bean(id,description,name,price) values (6, 'Delicious', 'Juice', 100);

--insert into restaurant_bean_drinks_menu(rest_id,drink_id) values (, );

insert into restaurant_bean_drinks_menu(rest_id,drink_id) values (1, 5);
insert into restaurant_bean_drinks_menu(rest_id,drink_id) values (1, 6);

--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (1, 6);
--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (1, 5);

--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (2, 5);
--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (2, 4);

--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (3, 4);
--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (3, 5);

--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (4, 5);
--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (4, 6);

--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (5, 6);
--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (5, 5);

--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (6, 5);
--insert into restaurant_bean_drinks_menu(restaurant_bean_id,drinks_menu_id) values (6, 4);

--insert into restaurant_bean_food_menu(rest_id,food_id) values (, );

insert into restaurant_bean_food_menu(rest_id,food_id) values (1, 2);
insert into restaurant_bean_food_menu(rest_id,food_id) values (1, 1);

--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (1, 1);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (2, 1);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (3, 2);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (4, 2);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (5, 3);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (6, 3);

--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (1, 2);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (2, 2);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (3, 3);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (4, 3);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (5, 1);
--insert into restaurant_bean_food_menu(restaurant_bean_id,food_menu_id) values (6, 2);