--D:\Suckhead\Fakultet\ISA\ISA_2016\IMAGES\no_image_found.jpg
--========================================
--USERS
--insert into user(id, email, first_name, last_name, password, profile_picture, username, friends_of_user_id) 
--values (, '', '', '', '', '', '', '');
--========================================
insert into user(id, email, first_name, last_name, password, profile_picture, username)
values (1, 'john.doe@gmail.com', 'John', 'Doe', 'test', 'NA', 'test1');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (2, 'jane.doe@gmail.com', 'Jane', 'Doe', 'test', 'NA', 'test2');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (3, 'carl_sagan@gmail.com', 'Carl', 'Sagan', 'test', 'NA', 'test3');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (4, 'tesla_rocks@gmail.com', 'Nikola', 'Tesla', 'test', 'NA', 'test4');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (5, 'rade_d@gmail.com', 'Rade', 'Doros', 'test', 'NA', 'test5');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (6, 'mironiko@gmail.com', 'Miroslav', 'Nikolic', 'test', 'NA', 'test6');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (7, 'kanovic@gmail.com', 'Predrag', 'Kanovic', 'test', 'NA', 'test7');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (8, 'nix_rich@gmail.com', 'Richard', 'Nixon', 'test', 'NA', 'test8');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (9, 'TRUMP@trumptowers.com', 'Donald', 'Trump', 'test', 'NA', 'test9');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (10, 'hildawg@gmail.com', 'Hillary', 'Clinton', 'test', 'NA', 'test10');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (11, 'imwithhilldawg@gmail.com', 'Bill', 'Clinton', 'test', 'NA', 'test11');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (12, 'free_the_slaves@yahoo.com', 'Abraham', 'Lincoln', 'test', 'NA', 'test12');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (13, 'nukes_shitao@cc_zero_hour.com', 'Shi', 'Tao', 'test', 'NA', 'test13');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (14, 'tanks_rock@cc_zero_hour.com', 'Gen', 'Kwai', 'test', 'NA', 'test14');


--=============================================
--RESTAURANT ZONES
--insert into restaurant_zone(id,name)
--values (, '', ,);
--=============================================
insert into restaurant_zone(id,name)
values (1, 'Garden');
insert into restaurant_zone(id,name)
values (2, 'Garden (Closed)');
insert into restaurant_zone(id,name)
values (3, 'Smoking');
insert into restaurant_zone(id,name)
values (4, 'No Smoking');
insert into restaurant_zone(id,name)
values (5, 'Patio');
insert into restaurant_zone(id,name)
values (6, 'Kids');
insert into restaurant_zone(id,name)
values (7, 'Main Hall');

--=============================================
--RESTAURANTS

--insert into restaurant(id, name, type,image) 
--values (, '', '',null);
--=============================================
insert into restaurant(id, name, type, image) 
values (1, 'Burger King', 'Fast Food','/assets/pictures/restaurant_pictures/1.jpg');

insert into restaurant(id, name, type, image) 
values (2, 'Jack-in-the-Box', 'Fast Food',null);

insert into restaurant(id, name, type, image) 
values (3, 'Big Pizza', 'Fast Food',null);

insert into restaurant(id, name, type, image) 
values (4, 'Pizza Hut', 'Fast Food',null);

insert into restaurant(id, name, type, image) 
values (5, 'Gordon Ramsay London', 'Fine Dining',null);

insert into restaurant(id, name, type, image) 
values (6, 'Lanterna', 'Fine Dining',null);

insert into restaurant(id, name, type, image) 
values (7, 'Wine Cellar', 'Fine Dining',null);

insert into restaurant(id, name, type, image) 
values (8, 'Afaria', 'Bistro','/assets/pictures/restaurant_pictures/8.jpg');

insert into restaurant(id, name, type, image) 
values (9, 'Allard', 'Bistro',null);

insert into restaurant(id, name, type, image) 
values (10, 'Noma', 'Fine Dining','/assets/pictures/restaurant_pictures/10.jpg');

insert into restaurant(id, name, type, image) 
values (11, 'Celler de Can Roca', 'Fine Dining',null);

insert into restaurant(id, name, type, image) 
values (12, 'Eleven Madison Park', 'Fine Dining',null);

insert into restaurant(id, name, type, image) 
values (13, 'Auberge Pyrenees Cevennes', 'Bistro',null);

insert into restaurant(id, name, type, image) 
values (14, 'Aux Lyonnais', 'Bistro',null);

insert into restaurant(id, name, type, image) 
values (15, 'Horseshoe Cafe', 'Diner','/assets/pictures/restaurant_pictures/15.jpg');

insert into restaurant(id, name, type, image) 
values (16, 'Palace Diner', 'Diner',null);

insert into restaurant(id, name, type, image) 
values (17, 'White Light Diner', 'Diner',null);

insert into restaurant(id, name, type, image) 
values (18, 'Ruths Diner', 'Diner',null);

insert into restaurant(id, name, type, image) 
values (19, 'Blue Benn', 'Diner',null);

insert into restaurant(id, name, type, image) 
values (20, 'Fremont Diner', 'Diner',null);

insert into restaurant(id, name, type, image) 
values (21, 'JC Irish', 'Sports Bar',null);

insert into restaurant(id, name, type, image) 
values (22, 'Twin Peaks', 'Sports Bar','/assets/pictures/restaurant_pictures/22.jpg');

insert into restaurant(id, name, type, image) 
values (23, 'The Pub at Monte Carlo', 'Sports Bar',null);

insert into restaurant(id, name, type, image) 
values (24, 'TAP', 'Sports Bar',null);

insert into restaurant(id, name, type, image) 
values (25, 'Crown & Anchor', 'Sports Bar',null);

insert into restaurant(id, name, type, image) 
values (26, 'Lagasses Stadium', 'Sports Bar',null);

--============================================
--RESTAURANT ZONE RELATION
--insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
--values (, , , );
--============================================
--=BURGER KING ZONES
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (1, 1, 4, 2, 5);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (2, 1, 4, 4, 2);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (3, 1, 3, 4, 1);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (4, 1, 3, 2, 5);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (5, 1, 3, 3, 2);

--=NOMA ZONES
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (6, 10, 4, 2, 5);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (7, 10, 4, 4, 5);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (8, 10, 3, 3, 5);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (9, 10, 3, 2, 5);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (10, 10, 2, 2, 5);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (11, 10, 2, 3, 2);

--=HORSESHOE CAFFE ZONES
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (12, 15, 4, 2, 5);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (13, 15, 4, 3, 3);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (14 ,15, 3, 4, 10);

--=TWIN PEAKS ZONES
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (15, 22, 4, 2, 12);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (16, 22, 4, 4, 8);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (17, 22, 3, 2, 10);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (18, 22, 7, 4, 10);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (19, 22, 7, 2, 10);
insert into restaurant_has_zones(id, rest_id, zone_id, table_for_x, amoun_of_tables)
values (20, 22, 7, 8, 2);


--=========================================================
--FOOD TYPES
--insert into restaurant_food_type(id, name)
--values(, '');
--=========================================================
insert into restaurant_food_type(id, name)
values(1, 'Serbian');
insert into restaurant_food_type(id, name)
values(2, 'Spanish');
insert into restaurant_food_type(id, name)
values(3, 'Mexian');
insert into restaurant_food_type(id, name)
values(4, 'Jamaican');
insert into restaurant_food_type(id, name)
values(5, 'Italian');
insert into restaurant_food_type(id, name)
values(6, 'Chinese');
insert into restaurant_food_type(id, name)
values(7, 'Japanese');
insert into restaurant_food_type(id, name)
values(8, 'Indian');
insert into restaurant_food_type(id, name)
values(9, 'US');
insert into restaurant_food_type(id, name)
values(10, 'British');
insert into restaurant_food_type(id, name)
values(11, 'Vietnamese');
insert into restaurant_food_type(id, name)
values(12, 'All Seafood');
insert into restaurant_food_type(id, name)
values(13, 'All Oriental');

--=========================================================
--PRODUCTS
--FOOD FROM 10001 TO 50000
--DRINKS FROM 50001
--insert into product(id, name, description, price, is_food)
--values (, '', '', );
--=========================================================
insert into product(id, name, description, price, is_food)
values (100001, 'Capricciosa, Italy', 'Most popular, standard pizza.', 5, TRUE);

insert into product(id, name, description, price, is_food)
values (100002, 'Quattro Formaggi, Italy', 'Pizza with four different types of cheese.', 7, TRUE);

insert into product(id, name, description, price, is_food)
values (100003, 'Beef Wellington, Italy', 'Famous bread crusted beef dish.', 15, TRUE);

insert into product(id, name, description, price, is_food)
values (100004, 'Seafood paella, Spain', 'Shrimp, lobster, mussels and cuttlefish combine with white rice and various herbs.', 14, TRUE);

insert into product(id, name, description, price, is_food)
values (100005, 'Som tam, Thailand', ' Thailands most famous salad, pound garlic and chilies with a mortar and pestle. ',14 , TRUE);

insert into product(id, name, description, price, is_food)
values (100006, 'Chicken rice, Singapore', 'Boiled chicken is served atop fragrant oily rice, with sliced cucumber.', 10, TRUE);

insert into product(id, name, description, price, is_food)
values (100007, 'Tacos, Mexico', 'A fresh, handmade tortilla stuffed with small chunks of grilled beef.', 3, TRUE);

insert into product(id, name, description, price, is_food)
values (100008, 'Buttered toast with Marmite, Britain', 'Marmelade and toast soaked in butter.', 4, TRUE);

insert into product(id, name, description, price, is_food)
values (100009, 'Marzipan, Germany', 'Ground almonds with sugar.', 10, TRUE);

insert into product(id, name, description, price, is_food)
values (100010, 'French toast, Hong Kong', 'Hong Kong-style French toast.', 11, TRUE);

insert into product(id, name, description, price, is_food)
values (100011, 'Chicken parm, Australia', 'Melted Parmesan and mozzarella cheese, and a peppery, garlicky tomato sauce.', 6, TRUE);

insert into product(id, name, description, price, is_food)
values (100012, 'Chili crab, Singapore', 'Spicy, sloppy, meaty specialty.', 11, TRUE);

insert into product(id, name, description, price, is_food)
values (100013, 'Fish ‘n’ chips, Britain', 'Sprinkled with salt, vinegar and dollops of tartar sauce.', 10, TRUE);

insert into product(id, name, description, price, is_food)
values (100014, 'Ankimo, Japan', 'The monkfish/anglerfish that unknowingly bestows its liver upon upscale sushi fans.', 11, TRUE);

insert into product(id, name, description, price, is_food)
values (100015, 'Montreal-style smoked meat, Canada', 'smoked beef sandwich medium-lean, heavy on the mustard.', 12, TRUE);

insert into product(id, name, description, price, is_food)
values (100016, ' Fajitas, Mexico', 'Meat, throw side servings of capsicum, onion, guacamole, sour cream.', 13, TRUE);

insert into product(id, name, description, price, is_food)
values (100017, 'Champ, Ireland', ' Mashed potato with spring onions.', 4, TRUE);

insert into product(id, name, description, price, is_food)
values (100018, ' Lasagna, Italy', ' pasta-layered, tomato-sauce-infused, minced-meat.', 10, TRUE);

insert into product(id, name, description, price, is_food)
values (100019, 'Croissant, France', 'Flaky pastry smothered in butter.', 2, TRUE);

insert into product(id, name, description, price, is_food)
values (100020, 'Arepas, Venezuela', 'A corn-dough patty that provides a savory canvas .', 4, TRUE);

insert into product(id, name, description, price, is_food)
values (100021, 'Kebab, Iran', 'REMOVE!', 1, TRUE);

insert into product(id, name, description, price, is_food)
values (100022, 'Kalua pig, United States', 'Whole pig roasted in an underground sand pit for six or seven hours.', 25, TRUE);

insert into product(id, name, description, price, is_food)
values (100023, 'Donuts, United States', 'Well known US snack.', 2, TRUE);

insert into product(id, name, description, price, is_food)
values (100024, 'Shepherd’s pie, Britain', 'Minced lamb topped with mashed potato', 13, TRUE);

insert into product(id, name, description, price, is_food)
values (100025, 'Chicken muamba, Gabon', 'Chicken, hot chili, garlic, tomato, pepper, salt, okra and palm butter', 10, TRUE);

insert into product(id, name, description, price, is_food)
values (500001, 'Geil Trocken Spätburgunder', 'Famous Wine 2015', 22, FALSE);

insert into product(id, name, description, price, is_food)
values (500002, 'Domaine Jorel, La Garrigue', 'Rare Wine 2003', 32, FALSE);

insert into product(id, name, description, price, is_food)
values (500003, 'Stoller Reserve Chardonnay', 'Famous Chardonnay 2014', 33, FALSE);

insert into product(id, name, description, price, is_food)
values (500004, 'Omero Cellars Pinot Gris', 'Well known Pinot, 2014', 22, FALSE);

insert into product(id, name, description, price, is_food)
values (500005, 'Ghost Pines Zinfandel', 'Excellent German Wine 2014', 16, FALSE);

insert into product(id, name, description, price, is_food)
values (500006, 'Guinness', 'Irelands famous beer.', 10, FALSE);

insert into product(id, name, description, price, is_food)
values (500007, 'Bud Light', 'US famous beer.', 4, FALSE);

insert into product(id, name, description, price, is_food)
values (500008, 'Budweiser', 'US most popular beer.', 5, FALSE);

insert into product(id, name, description, price, is_food)
values (500009, 'Skol', 'Famous Brazillian beer.', 2, FALSE);

insert into product(id, name, description, price, is_food)
values (500010, 'Heineken', 'Best beer from the Netherlands', 10, FALSE);

insert into product(id, name, description, price, is_food)
values (500011, 'Coca-Cola', 'US famous soft drink.', 2, FALSE);

insert into product(id, name, description, price, is_food)
values (500012, 'Pepsi', 'US second famous soft drink.', 2, FALSE);

insert into product(id, name, description, price, is_food)
values (500013, 'Sprite', 'Refreshing soft drink.', 2, FALSE);

insert into product(id, name, description, price, is_food)
values (500014, 'Lemonade', 'Ice cold drink made from fresh lemons.', 3, FALSE);

insert into product(id, name, description, price, is_food)
values (500015, 'Orangina', 'Best soft drink from wherever the hell it is.', 999, FALSE);

--========================================
--RESTAURANT DRINKS

--insert into restaurant_drinks_menu(rest_id, drink_id)
--values(, );
--========================================

--TWIN PEAKS DRINKS - SPORTS BAR
insert into restaurant_drinks_menu(rest_id, drink_id)
values(22, 500010);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(22, 500009);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(22, 500008);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(22, 500007);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(22, 500006);

--HORSESHOE CAFE DRINKS - DINER
insert into restaurant_drinks_menu(rest_id, drink_id)
values(15, 500015);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(15, 500014);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(15, 500012);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(15, 500011);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(15, 500010);

--AFARIA DRINKS - BISTRO
insert into restaurant_drinks_menu(rest_id, drink_id)
values(8, 500003);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(8, 500007);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(8, 500015);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(8, 500014);

--BURGER KING DRINKS - FAST FOOD
insert into restaurant_drinks_menu(rest_id, drink_id)
values(1, 500007);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(1, 500008);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(1, 500011);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(1, 500012);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(1, 500013);

--NOMA DRINKS - FINE DINING
insert into restaurant_drinks_menu(rest_id, drink_id)
values(10, 500001);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(10, 500002);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(10, 500006);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(10, 500011);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(10, 500010);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(10, 500008);
insert into restaurant_drinks_menu(rest_id, drink_id)
values(10, 500009);

--=================================================
--RESTAURANT FOOD MENU
--insert into restaurant_food_menu(rest_id,food_id)
--values (, );
--=================================================

--TWIN PEAKS FOOD - SPORTS BAR
insert into restaurant_food_menu(rest_id,food_id)
values (22, 100001);
insert into restaurant_food_menu(rest_id,food_id)
values (22, 100002);
insert into restaurant_food_menu(rest_id,food_id)
values (22, 100010);
insert into restaurant_food_menu(rest_id,food_id)
values (22, 100023);
insert into restaurant_food_menu(rest_id,food_id)
values (22, 100018);
insert into restaurant_food_menu(rest_id,food_id)
values (22, 100008);

--HORSESHOE CAFE FOOD - DINER
insert into restaurant_food_menu(rest_id,food_id)
values (15, 100001);
insert into restaurant_food_menu(rest_id,food_id)
values (15, 100010);
insert into restaurant_food_menu(rest_id,food_id)
values (15, 100023);
insert into restaurant_food_menu(rest_id,food_id)
values (15, 100018);
insert into restaurant_food_menu(rest_id,food_id)
values (15, 100008);
insert into restaurant_food_menu(rest_id,food_id)
values (15, 100006);
insert into restaurant_food_menu(rest_id,food_id)
values (15, 100015);

--AFARIA FOOD - BISTRO
insert into restaurant_food_menu(rest_id,food_id)
values (8, 100002);
insert into restaurant_food_menu(rest_id,food_id)
values (8, 100018);
insert into restaurant_food_menu(rest_id,food_id)
values (8, 100008);
insert into restaurant_food_menu(rest_id,food_id)
values (8, 100006);
insert into restaurant_food_menu(rest_id,food_id)
values (8, 100015);

--BURGER KING FOOD - FAST FOOD
insert into restaurant_food_menu(rest_id,food_id)
values (1, 100001);
insert into restaurant_food_menu(rest_id,food_id)
values (1, 100002);
insert into restaurant_food_menu(rest_id,food_id)
values (1, 100010);
insert into restaurant_food_menu(rest_id,food_id)
values (1, 100023);
insert into restaurant_food_menu(rest_id,food_id)
values (1, 100018);

--NOMA FOOD - FINE DINING
insert into restaurant_food_menu(rest_id,food_id)
values (10, 100001);
insert into restaurant_food_menu(rest_id,food_id)
values (10, 100002);
insert into restaurant_food_menu(rest_id,food_id)
values (10, 100018);
insert into restaurant_food_menu(rest_id,food_id)
values (10, 100008);
insert into restaurant_food_menu(rest_id,food_id)
values (10, 100006);

--==============================================
--RESTAURANT SERVES FOOD TYPES
--insert into restaurant_serves_cuisines(rest_id,type_id)
--values(,);
--==============================================

--TWIN PEAKS FOOD TYPES - SPORTS BAR
insert into restaurant_serves_cuisines(rest_id,type_id)
values (22, 5);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (22, 6);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (22, 9);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (22, 10);

--HORSESHOE CAFE FOOD TYPES - DINER
insert into restaurant_serves_cuisines(rest_id,type_id)
values (15, 5);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (15, 6);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (15, 9);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (15, 10);

--AFARIA FOOD TYPES- BISTRO
insert into restaurant_serves_cuisines(rest_id,type_id)
values (8, 5);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (8, 6);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (8, 9);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (8, 10);

--BURGER KING FOOD TYPES - FAST FOOD
insert into restaurant_serves_cuisines(rest_id,type_id)
values (1, 5);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (1, 6);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (1, 3);

--NOMA FOOD TYPES- FINE DINING
insert into restaurant_serves_cuisines(rest_id,type_id)
values (10, 5);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (10, 6);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (10, 9);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (10, 3);
insert into restaurant_serves_cuisines(rest_id,type_id)
values (10, 10);