
--========================================
--USERS
--insert into user(id, email, first_name, last_name, password, profile_picture, username, friends_of_user_id) 
--values (, '', '', '', '', '', '', '');
--========================================
insert into user(id, auth_code, email, first_name, last_name, password, profile_picture, username)
--values (1, 'john.doe@gmail.com', 'John', 'Doe', 'test', 'NA', 'test1');
values(1, 'auth0|58b6a6dd9726cd081e6c72bb', 'majasumaruna@gmail.com', 'Maja', 'Sumaruna', NULL, 'https://s.gravatar.com/avatar/c17906f01707a291888b9576a1eaf29a?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2Fma.png', 'majasumaruna');

insert into user(id, auth_code, email, first_name, last_name, password, profile_picture, username) 
--values (2, 'jane.doe@gmail.com', 'Jane', 'Doe', 'test', 'NA', 'test2');
values(2, 'auth0|58b5ff28b33c5546787aae7d', 'stkosijer@gmail.com', 'Ste', 'Vandam', NULL, 'https://s.gravatar.com/avatar/fe222883da82d3bbe204b4977ef574b9?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2Fst.png', 'stkosijer');

insert into user 
values(34, 'google-oauth2|110314511839859925553', 'milanns@live.com', 'djuka', 'djuka', NULL, 'https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg', NULL, 'milanns');

insert into user
values(35, 'google-oauth2|108029269584602236134', 'internet.softverske.3.user@gmail.com', 'ua', 'account', NULL, 'https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg', NULL, 'internet.softverske.3.user');

insert into user
values(36, 'google-oauth2|114881230620212881542', 'internet.softverske.1.user@gmail.com', 'ua', 'account', NULL, 'https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg', NULL, 'internet.softverske.1.user');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (3, 'carl_sagan@gmail.com', 'Carl', 'Sagan', 'test', 'NA', 'test3');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (4, 'tesla_rocks@gmail.com', 'Nikola', 'Tesla', 'test', 'NA', 'test4');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (5, 'rade_d@gmail.com', 'Rade', 'Doros', 'test', 'NA', 'test5');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (6, 'mironiko@gmail.com', 'Miroslav', 'Nikolic', 'test', 'NA', 'test6');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (7, 'kanovic@gmail.com', 'NemPojma', 'Kanovic', 'test', 'NA', 'test7');

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

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (15, 'maynard_keenan@caduceus.com', 'Maynard', 'Keenan', 'test', 'NA', 'maynard_tool');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (16, 'draper.don@scdp.com', 'Donald', 'Draper', 'test', 'NA', 'don_mad_man');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (17, 'roger.sterling@scdp.com', 'Roger', 'Sterling', 'test', 'NA', 'roger_mad_man');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (18, 'deniro@gmail.com', 'Robert', 'DeNiro', 'test', 'NA', 'robert_niro');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (19, 'filler@gmail.com', 'Filler', 'User', 'test', 'NA', 'filler_user_01');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (20, 'spyder@gmail.com', 'Peter', 'Parker', 'test', 'NA', 'pete_parker');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (21, 'filler02@gmail.com', 'Filler', 'User', 'test', 'NA', 'filler_user_02');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (22, 'frederick.white40@example.com', 'Frederick', 'White', 'test', 'NA', 'predator');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (23, 'becky.adams89@example.com', 'Becky', 'Adams', 'test', 'NA', 'girlygurrrl');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (24, 'caroline.collins23@example.com', 'Caroline', 'Collins', 'test', 'NA', 'whatevz');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (26, 'austin.stephens92@example.com', 'Austin', 'Stephens', 'test', 'NA', 'ambrosia');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (27, 'ivan.larson32@example.com', 'Ivan', 'Larson', 'test', 'NA', '11111');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (28, 'hugh.bailey56@example.com', 'Hugh', 'Bailey', 'test', 'NA', 'espresso');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (29, 'ennifer.davis23@example.com', 'Jennifer', 'Davis', 'test', 'NA', 'married');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (30, 'edna.chapman81@example.com', 'Edna', 'Chapman', 'test', 'NA', 'ashley1');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (31, 'heidi.romero92@example.com', 'Heidi', 'Romero', 'test', 'NA', '78945612');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (32, 'josephine.mendoza21@example.com', 'Josephine', 'Mendoza', 'test', 'NA', 'server');

insert into user(id, email, first_name, last_name, password, profile_picture, username) 
values (33, 'mae.henderson84@example.com', 'Mae', 'Henderson', 'test', 'NA', 'jack1');


--=============================================
--FRIENDSHIPS

--insert into Friendship(id, status, originator_id , recipient_id) 
--values (, '', '','');
--=============================================

insert into Friendship(id, status, originator_id , recipient_id) 
values (1, 'ACCEPTED', 1, 2);

insert into Friendship(id, status, originator_id , recipient_id) 
values (2, 'ACCEPTED', 1, 3);

insert into Friendship(id, status, originator_id , recipient_id) 
values (3, 'ACCEPTED', 1, 4);

insert into Friendship(id, status, originator_id , recipient_id) 
values (4, 'ACCEPTED', 1, 5);

insert into Friendship(id, status, originator_id , recipient_id) 
values (5, 'ACCEPTED', 1, 6);

--=============================================
--RESTAURANTS

--insert into restaurant(id, name, type,image) 
--values (, '', '',null);
--=============================================
insert into restaurant(id, name, type, image,lng,lat )
values (1, 'Burger King', 'Fast Food','/assets/pictures/restaurant_pictures/1.jpg',19.829936027526855,45.27331602606693);

insert into restaurant(id, name, type, image,lng,lat )
values (2, 'Jack-in-the-Box', 'Fast Food','/assets/pictures/restaurant_pictures/2.jpg',45.27077911652709,19.838132858276367);

insert into restaurant(id, name, type, image,lng,lat )
values (3, 'Big Pizza', 'Fast Food','/assets/pictures/restaurant_pictures/3.jpg',45.26739639407451,19.845685958862305);

insert into restaurant(id, name, type, image,lng,lat )
values (4, 'Pizza Hut', 'Fast Food','/assets/pictures/restaurant_pictures/4.jpg',45.2657653665743,19.83530044555664);

insert into restaurant(id, name, type, image,lng,lat )
values (5, 'Gordon Ramsay London', 'Fine Dining','/assets/pictures/restaurant_pictures/5.jpg',45.26099283176157,19.841909408569336);

insert into restaurant(id, name, type, image,lng,lat )
values (6, 'Lanterna', 'Fine Dining','/assets/pictures/restaurant_pictures/6.jpg',45.257790779737306,19.83409881591797);

insert into restaurant(id, name, type, image,lng,lat )
values (7, 'Wine Cellar', 'Fine Dining','/assets/pictures/restaurant_pictures/7.jpg',45.25362179991922,19.83658790588379);

insert into restaurant(id, name, type, image,lng,lat )
values (8, 'Afaria', 'Bistro','/assets/pictures/restaurant_pictures/8.jpg',45.24908995297326,19.83881950378418);

insert into restaurant(id, name, type, image,lng,lat )
values (9, 'Allard', 'Bistro','/assets/pictures/restaurant_pictures/9.jpg',45.246189381155695,19.840106964111328);

insert into restaurant(id, name, type, image,lng,lat )
values (10, 'Noma', 'Fine Dining','/assets/pictures/restaurant_pictures/10.jpg',45.24534335314212,19.84027862548828);

insert into restaurant(id, name, type, image,lng,lat )
values (11, 'Celler de Can Roca', 'Fine Dining','/assets/pictures/restaurant_pictures/11.jpg',45.24165694117146,19.842510223388672);

insert into restaurant(id, name, type, image,lng,lat )
values (12, 'Eleven Madison Park', 'Fine Dining','/assets/pictures/restaurant_pictures/12.jpg',45.24026692045921,19.843626022338867);

insert into restaurant(id, name, type, image,lng,lat )
values (13, 'Auberge Pyrenees Cevennes', 'Bistro','/assets/pictures/restaurant_pictures/13.jpg',45.23778897310704,19.845256805419922);

insert into restaurant(id, name, type, image,lng,lat )
values (14, 'Aux Lyonnais', 'Bistro','/assets/pictures/restaurant_pictures/14.jpg',45.23694281999268,19.839763641357422);

insert into restaurant(id, name, type, image,lng,lat )
values (15, 'Horseshoe Cafe', 'Diner','/assets/pictures/restaurant_pictures/15.jpg',45.2396625530206,19.81856346130371);

insert into restaurant(id, name, type, image,lng,lat )
values (16, 'Palace Diner', 'Diner','/assets/pictures/restaurant_pictures/16.jpg',45.24087128146923,19.807233810424805);

insert into restaurant(id, name, type, image,lng,lat )
values (17, 'White Light Diner', 'Diner','/assets/pictures/restaurant_pictures/17.jpg',45.24135476564864,19.8068904876709);

insert into restaurant(id, name, type, image,lng,lat )
values (18, 'Ruths Diner', 'Diner','/assets/pictures/restaurant_pictures/18.jpg',45.24262389204447,19.804487228393555);

insert into restaurant(id, name, type, image,lng,lat )
values (19, 'Blue Benn', 'Diner','/assets/pictures/restaurant_pictures/19.jpg',45.23899774141306,19.798736572265625);

insert into restaurant(id, name, type, image,lng,lat )
values (20, 'Fremont Diner', 'Diner','/assets/pictures/restaurant_pictures/20.jpg',null,null);

insert into restaurant(id, name, type, image,lng,lat )
values (21, 'JC Irish', 'Sports Bar','/assets/pictures/restaurant_pictures/21.jpg',null,null);

insert into restaurant(id, name, type, image,lng,lat )
values (22, 'Twin Peaks', 'Sports Bar','/assets/pictures/restaurant_pictures/22.jpg',null,null);

insert into restaurant(id, name, type, image,lng,lat )
values (23, 'The Pub at Monte Carlo', 'Sports Bar','/assets/pictures/restaurant_pictures/23.jpg',null,null);

insert into restaurant(id, name, type, image,lng,lat )
values (24, 'TAP', 'Sports Bar','/assets/pictures/restaurant_pictures/24.jpg',null,null);

insert into restaurant(id, name, type, image,lng,lat )
values (25, 'Crown & Anchor', 'Sports Bar','/assets/pictures/restaurant_pictures/25.jpg',null,null);

insert into restaurant(id, name, type, image,lng,lat )
values (26, 'Lagasses Stadium', 'Sports Bar','/assets/pictures/restaurant_pictures/26.jpg',null,null);

--=====================================
--RESTAURANT ZONES
--insert into restaurant_zone(id, name, number_of_tables, tables_for_x, restaurant_id, deleted)
--values (, '', , , ,);
--=====================================

--==BURGER KING ZONES:
insert into restaurant_zone(id, name, tables_for_x,number_of_tables,  restaurant_id, deleted)
values (1, 'Main Hall', 2, 5, 1, 0);
insert into restaurant_zone(id, name, tables_for_x,number_of_tables,  restaurant_id, deleted)
values (2, 'Garden', 4, 2, 1, 0);
insert into restaurant_zone(id, name, tables_for_x,number_of_tables,  restaurant_id, deleted)
values (3, 'Smoking', 4, 5, 1, 0);
insert into restaurant_zone(id, name, tables_for_x,number_of_tables,  restaurant_id, deleted)
values (4, 'No Smoking', 4, 5, 1, 0);

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
--insert into product(id, name, description, price, is_food,type)
--values (, '', '', );
--=========================================================

insert into product(id, name, description, price, is_food,prod_type)
values (100001, 'Capricciosa, Italy', 'Most popular, standard pizza.', 5, TRUE,'Italian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100002, 'Quattro Formaggi, Italy', 'Pizza with four different types of cheese.', 7, TRUE,'Italian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100003, 'Beef Wellington, Italy', 'Famous bread crusted beef dish.', 15, TRUE,'Italian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100004, 'Seafood paella, Spain', 'Shrimp, lobster, mussels and cuttlefish combine with white rice and various herbs.', 14, TRUE,'Spanish cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100005, 'Som tam, Thailand', ' Thailands most famous salad, pound garlic and chilies with a mortar and pestle. ',14 , TRUE,'Asian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100006, 'Chicken rice, Singapore', 'Boiled chicken is served atop fragrant oily rice, with sliced cucumber.', 10, TRUE,'Asian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100007, 'Tacos, Mexico', 'A fresh, handmade tortilla stuffed with small chunks of grilled beef.', 3, TRUE,'Spanish cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100008, 'Buttered toast with Marmite, Britain', 'Marmelade and toast soaked in butter.', 4, TRUE,'Britain cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100009, 'Marzipan, Germany', 'Ground almonds with sugar.', 10, TRUE,'Britain cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100010, 'French toast, Hong Kong', 'Hong Kong-style French toast.', 11, TRUE,'Asian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100011, 'Chicken parm, Australia', 'Melted Parmesan and mozzarella cheese, and a peppery, garlicky tomato sauce.', 6, TRUE,'Asian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100012, 'Chili crab, Singapore', 'Spicy, sloppy, meaty specialty.', 11, TRUE,'Asian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100013, 'Fish ‘n’ chips, Britain', 'Sprinkled with salt, vinegar and dollops of tartar sauce.', 10, TRUE,'Britain cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100014, 'Ankimo, Japan', 'The monkfish/anglerfish that unknowingly bestows its liver upon upscale sushi fans.', 11, TRUE,'Asian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100015, 'Montreal-style smoked meat, Canada', 'smoked beef sandwich medium-lean, heavy on the mustard.', 12, TRUE,'Britain cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100016, ' Fajitas, Mexico', 'Meat, throw side servings of capsicum, onion, guacamole, sour cream.', 13, TRUE,'Spanish cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100017, 'Champ, Ireland', ' Mashed potato with spring onions.', 4, TRUE,'Britain cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100018, ' Lasagna, Italy', ' pasta-layered, tomato-sauce-infused, minced-meat.', 10, TRUE,'Italian cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100019, 'Croissant, France', 'Flaky pastry smothered in butter.', 2, TRUE,'French cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100020, 'Arepas, Venezuela', 'A corn-dough patty that provides a savory canvas .', 4, TRUE,'Spanish cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100021, 'Kebab, Iran', 'REMOVE!', 1, TRUE,null);

insert into product(id, name, description, price, is_food,prod_type)
values (100022, 'Kalua pig, United States', 'Whole pig roasted in an underground sand pit for six or seven hours.', 25, TRUE,'Britain cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100023, 'Donuts, United States', 'Well known US snack.', 2, TRUE,'Britain cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100024, 'Shepherd’s pie, Britain', 'Minced lamb topped with mashed potato', 13, TRUE,'Britain cook');

insert into product(id, name, description, price, is_food,prod_type)
values (100025, 'Chicken muamba, Gabon', 'Chicken, hot chili, garlic, tomato, pepper, salt, okra and palm butter', 10, TRUE,'African cook');

insert into product(id, name, description, price, is_food,prod_type)
values (500001, 'Geil Trocken Spätburgunder', 'Famous Wine 2015', 22, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500002, 'Domaine Jorel, La Garrigue', 'Rare Wine 2003', 32, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500003, 'Stoller Reserve Chardonnay', 'Famous Chardonnay 2014', 33, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500004, 'Omero Cellars Pinot Gris', 'Well known Pinot, 2014', 22, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500005, 'Ghost Pines Zinfandel', 'Excellent German Wine 2014', 16, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500006, 'Guinness', 'Irelands famous beer.', 10, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500007, 'Bud Light', 'US famous beer.', 4, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500008, 'Budweiser', 'US most popular beer.', 5, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500009, 'Skol', 'Famous Brazillian beer.', 2, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500010, 'Heineken', 'Best beer from the Netherlands', 10, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500011, 'Coca-Cola', 'US famous soft drink.', 2, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500012, 'Pepsi', 'US second famous soft drink.', 2, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500013, 'Sprite', 'Refreshing soft drink.', 2, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500014, 'Lemonade', 'Ice cold drink made from fresh lemons.', 3, FALSE,'Drink');

insert into product(id, name, description, price, is_food,prod_type)
values (500015, 'Orangina', 'Best soft drink from wherever the hell it is.', 999, FALSE,'Drink');

--========================================
--Table

--insert into tables(table_id,max_people,image,zone_id)
--values(, );
--=========================================
--insert into restaurant_table(table_id, max_people,image,zone_id)
--values (11111, 4, '/assets/pictures/restaurant_pictures/10.jpg',1);
--=======
--insert into restaurant_table(table_id,max_people,status,restaurant_zone_id)
--values(, );
--=========================================



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

--=============================================
--MANAGERS
--=============================================
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'MANAGER',null,null,1,null);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'MANAGER',null,null,2,null);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'MANAGER',null,null,3,null);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'MANAGER',null,null,4,null);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'MANAGER',null,null,34,null);

--=============================================
--OTHER EMPLOYEES
--=============================================
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'WAITER',null,null,5,1);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'WAITER',null,null,6,1);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'WAITER',null,null,7,1);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'WAITER',null,null,8,1);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'WAITER',null,null,9,2);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'WAITER',null,null,10,2);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'COOK',null,null,11,2);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'COOK',null,null,12,2);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'BARTENDER',null,null,13,3);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'BARTENDER',null,null,14,3);
insert into employee(date_of_birth,role,shoe_size,suit_size,user_id,works_in_restaurant)
values (766879200000,'BARTENDER',null,null,15,3);

--=====================================
--STOLOVI
--=====================================
insert into restaurant_table(table_id, max_people,status,restaurant_zone_id,image)
values (1, 4, 'FREE', 1,'/assets/pictures/1375.jpg');

insert into restaurant_table(table_id, max_people,status,restaurant_zone_id,image)
values (2, 2, 'FREE', 1,'/assets/pictures/1375.jpg');

insert into restaurant_table(table_id, max_people,status,restaurant_zone_id,image)
values (3, 6, 'FREE', 1,'/assets/pictures/1375.jpg');

insert into restaurant_table(table_id, max_people,status,restaurant_zone_id,image)
values (4, 4, 'FREE', 1,'/assets/pictures/1375.jpg');

insert into restaurant_table(table_id, max_people,status,restaurant_zone_id,image)
values (5, 6, 'FREE', 1,'/assets/pictures/1375.jpg');

--=====================================
--EMPLOYEE SCHEDZZZ
--=====================================
insert into employee_schedule(id,to_date,from_date,date_date,for_employee,table_table_id)
values(1,1000000100000,1000000000000,1000000100000,5,1);
insert into employee_schedule(id,to_date,from_date,date_date,for_employee,table_table_id)
values(2,1000000100000,1000000000000,1000000100000,6,1);
insert into employee_schedule(id,to_date,from_date,date_date,for_employee,table_table_id)
values(3,1000000100000,1000000000000,1000000100000,7,1);
insert into employee_schedule(id,to_date,from_date,date_date,for_employee,table_table_id)
values(4,1000000100000,1000000000000,1000000100000,8,1);

--=====================================
--Orders
--=====================================



--=====================================
--MANAGERS MANAGING RESTAURANTS
--=====================================

insert into manages_restaurants(rest_id,manager_id)
values(1,34);
insert into manages_restaurants(rest_id,manager_id)
values(2,34);
insert into manages_restaurants(rest_id,manager_id)
values(3,34);
insert into manages_restaurants(rest_id,manager_id)
values(4,34);

--==1,2,3 holds 1,2,3
insert into manages_restaurants(rest_id,manager_id)
values(1,1);
insert into manages_restaurants(rest_id,manager_id)
values(1,2);
insert into manages_restaurants(rest_id,manager_id)
values(1,3);
insert into manages_restaurants(rest_id,manager_id)
values(2,1);
insert into manages_restaurants(rest_id,manager_id)
values(2,2);
insert into manages_restaurants(rest_id,manager_id)
values(2,3);
insert into manages_restaurants(rest_id,manager_id)
values(3,1);
insert into manages_restaurants(rest_id,manager_id)
values(3,2);
insert into manages_restaurants(rest_id,manager_id)
values(3,3);

--==4 holds 4,5
insert into manages_restaurants(rest_id,manager_id)
values(4,4);
insert into manages_restaurants(rest_id,manager_id)
values(5,4);

--=====================================
--TESTING DJuKA
--=====================================

insert into restaurant_registry(id,restaurant_name,seen,type,status,deleted,registering_by)
values(1,'A new Hope',0,'Fast Food','PENDING',0,1);
insert into restaurant_registry(id,restaurant_name,seen,type,status,deleted,registering_by)
values(2,'Dashing Prince',0,'Diner','PENDING',0,1);
insert into restaurant_registry(id,restaurant_name,seen,type,status,deleted,registering_by)
values(3,'The Dutchman',0,'Sports Bar','PENDING',0,1);
insert into restaurant_registry(id,restaurant_name,seen,type,status,deleted,registering_by)
values(4,'Buckys Fish and Grill',0,'Fast Food','PENDING',0,4);

--==============================================
--EXISTING DELIVERERS
--==============================================
insert into deliverer(user_id,request_status)
values(16,'PENDING');
insert into deliverer(user_id,request_status)
values(17,'ACCEPTED');
insert into deliverer(user_id,request_status)
values(18,'ACCEPTED');
insert into deliverer(user_id,request_status,first_login) values(1,'ACCEPTED',1);

--==============================================================================================
--RESERVATION 
--==============================================================================================

insert into reservation(id, end_date, start_date, table_id)
values('1', '1488078216869', '1488074616869', '3');
insert into reservation(id, end_date, start_date, table_id)
values('2', '1488078216869', '1488074616869', '4');
insert into reservation(id, end_date, start_date, table_id)
values('3', '1488078216869', '1488074616869', '5');
insert into reservation(id, end_date, start_date, table_id)
values('4', '1488341227162', '1488330427000', '4');
insert into reservation(id, end_date, start_date, table_id)
values('5', '1488341227162', '1488330427000', '2');
insert into reservation(id, end_date, start_date, table_id)
values('6', '1488341227162', '1488330427000', '5');
insert into reservation(id, end_date, start_date, table_id)
values('7', '1488341227162', '1488330427000', '3');
insert into reservation(id, end_date, start_date, table_id)
values('8', '1488632827162', '1488622027000', '4');
insert into reservation(id, end_date, start_date, table_id)
values('9', '1488632827162', '1488622027000', '3');
insert into reservation(id, end_date, start_date, table_id)
values('10', '1488632827162', '1488622027000', '5');

--reservation that passed
insert into reservation(id, end_date, start_date, table_id)
values('11', '1486632827162', '1486622027000', '4');
insert into reservation(id, end_date, start_date, table_id)
values('12', '1486632827162', '1486622027000', '3');
insert into reservation(id, end_date, start_date, table_id)
values('13', '1486632827162', '1486622027000', '5');

--==============================================================================================
--RESERVATION CALLS
--==============================================================================================

insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('1', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '1');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('2', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '2');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('3', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '3');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('4', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '4');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('5', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '5');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('6', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '6');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('7', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '7');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('8', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '8');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('9', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '9');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('10', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '1', '10');


--reservation calls that passed

insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('11', '3', NULL, '0', '5', 'ACCEPTED', '5', NULL, NULL, '1', '1', '11');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('12', '4', NULL, '0', '3', 'ACCEPTED', '4', NULL, NULL, '1', '1', '12');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('13', '5', NULL, '0', '3', 'ACCEPTED', '2', NULL, NULL, '1', '1', '13');

insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('15', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '2', '13');
insert into reservation_call(id, food_rate, keygen, make_order_fast, restaurant_rate, status, waiter_rate, drink, food, originator_id, recipient_id, reservation)
values('14', '0', NULL, '0', '0', 'ACCEPTED', '0', NULL, NULL, '1', '2', '12');


--===================================================
--DELIVERY BIDS ORDERS ETC ...
--===================================================
--==orders

insert into delivery_order(id,date_from,date_to,made_by,for_restaurant_id,accepted_user_id,price_accepted)
values(1,1487718000000,1493071200000,1,1,17,null);
insert into delivery_order(id,date_from,date_to,made_by,for_restaurant_id,accepted_user_id,price_accepted)
values(2,1487718000000,1493071200000,1,1,17,null);
insert into delivery_order(id,date_from,date_to,made_by,for_restaurant_id,accepted_user_id,price_accepted)
values(3,1487718000000,1493071200000,1,1,18,null);
insert into delivery_order(id,date_from,date_to,made_by,for_restaurant_id,accepted_user_id,price_accepted)
values(4,1487718000000,1493071200000,1,1,18,null);
insert into delivery_order(id,date_from,date_to,made_by,for_restaurant_id,accepted_user_id,price_accepted)
values(5,1487718000000,1493071200000,1,1,null,null);
insert into delivery_order(id,date_from,date_to,made_by,for_restaurant_id,accepted_user_id,price_accepted)
values(6,1487718000000,1493071200000,1,1,null,null);

--==order items
--==(1)
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (1,100,'cabbage',1);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (2,10,'carrots',1);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (3,20,'tomatos',1);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (4,400,'rivets',1);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (5,50,'screws',1);
--==(2)
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (6,600,'birds',2);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (7,40,'chicken',2);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (8,140,'crabs',2);
--==(3)
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (9,22,'bolts',3);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (10,12,'AK-47',3);
--==(4)
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (11,100,'cabbage',4);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (12,10,'carrots',4);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (13,40,'chicken',4);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (14,140,'crabs',4);
--==(5)
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (15,12,'AK-47',5);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (16,600,'birds',5);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (17,40,'chicken',5);
--==(6)
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (18,10,'carrots',6);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (19,40,'chicken',6);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (20,100,'cabbage',6);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (21,10,'carrots',6);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (22,20,'tomatos',6);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (23,400,'rivets',6);
	insert into delivery_order_item(id,item_amount,item_name,belongs_to_order) values (24,50,'screws',6);

--==order bids
	--==by user 17
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(1,'ACCEPTED',100,0,17,1,1);
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(2,'ACCEPTED',200,0,17,2,1);
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(3,'DECLINED',300,0,17,3,1);
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(4,'DECLINED',400,0,17,4,1);
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(5,'PENDING',500,0,17,5,1);
	--==by user 18
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(6,'DECLINED',200,0,18,2,1);
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(7,'ACCEPTED',400,0,18,3,1);
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(8,'ACCEPTED',500,0,18,4,1);
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(9,'PENDING',600,0,18,5,1);
		insert into delivery_order_bid(id,bid_status,bidding_price,seen_status,made_by_deliverer_user_id,made_for_order_id,made_for_restaurant_id)
		values(10,'PENDING',800,0,18,6,1);

--===============================
--==REVIEWS
--===============================
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(1		,	1,	null,	5,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(2		,	1,	null,	5,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(3		,	2,	null,	5,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(4		,	2,	null,	5,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(5		,	1,	null,	5,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(6		,	1,	null,	6,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(7		,	3,	null,	6,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(8		,	3,	null,	null,	100001,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(9		,	4,	null,	null,	100001,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(10	,	4,	null,	null,	100001,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(11	,	4,	null,	null,	100001,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(12	,	2,	null,	null,	100001,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(13	,	2,	null,	null,	100002,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(14	,	4,	null,	null,	100002,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(15	,	3,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(16	,	1,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(17	,	1,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(18	,	5,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(19	,	5,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(20	,	1,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(21	,	1,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(22	,	3,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(23	,	5,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(24	,	2,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(25	,	2,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(26	,	5,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(27	,	5,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(28	,	5,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(29	,	5,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(30	,	5,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(31	,	5,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(32	,	4,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(33	,	1,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(34	,	1,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(35	,	1,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(36	,	1,	null,	5,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(37	,	2,	null,	6,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(38	,	2,	null,	7,	null,	null);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(39	,	1,	null,	null,	100001,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(40	,	4,	null,	null,	100002,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(41	,	4,	null,	null,	100002,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(42	,	4,	null,	null,	100002,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(43	,	1,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(44	,	2,	null,	null,	null,	1);
insert into review(id,grade,short_description,for_employee,for_product,for_restaurant) values(45	,	1,	null,	null,	null,	1);

--===============================
--BILLS
--===============================
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(1	,	100,	1486681200000,	5,	1);	--02/10/2017
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(2	,	100,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(3	,	100,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(4	,	100,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(5	,	100,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(6	,	100,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(7	,	100,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(8	,	100,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(9	,	100,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(10	,	100,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(11	,	200,	1486681200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(12	,	200,	1486681200000,	5,	1); 
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(13	,	200,	1486767600000,	5,	1);--02/11/2017
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(14	,	200,	1486767600000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(15	,	200,	1486767600000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(16	,	200,	1486767600000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(17	,	200,	1486767600000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(18	,	200,	1486767600000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(19	,	200,	1486767600000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(20	,	200,	1486854000000,	6,	1);--02/12/2017
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(21	,	200,	1486854000000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(22	,	200,	1486854000000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(23	,	300,	1486854000000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(24	,	300,	1486854000000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(25	,	300,	1486854000000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(26	,	300,	1486854000000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(27	,	300,	1486854000000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(28	,	300,	1486854000000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(29	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(30	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(31	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(32	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(33	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(34	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(35	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(36	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(37	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(38	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(39	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(40	,	300,	1486854000000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(41	,	300,	1487026800000,	5,	1);--02/14/2017
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(42	,	225,	1487026800000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(43	,	225,	1487026800000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(44	,	225,	1487026800000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(45	,	225,	1487026800000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(46	,	225,	1487113200000,	5,	1);--02/15/2017
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(47	,	225,	1487113200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(48	,	225,	1487113200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(49	,	225,	1487113200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(50	,	225,	1487113200000,	5,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(51	,	225,	1487113200000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(52	,	155,	1487113200000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(53	,	155,	1487199600000,	6,	1);--02/16/2017
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(54	,	155,	1487199600000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(55	,	155,	1487199600000,	6,	1);
--provera da ne uzme izvan godine
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(56	,	155,	1514761200000,	6,	1);--01/01/2018 0:00:00
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(57	,	155,	1514761200000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(58	,	155,	1514761200000,	6,	1);

insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(59	,	155,	1483225199000,	6,	1);--12/31/2016 23:59:59
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(60	,	155,	1483225199000,	6,	1);
insert into bill(id,cash,date_of_transaction,employee_user_id,restaurant_id) values(61	,	155,	1483225199000,	6,	1);

--nije validan test podatak
--potrebno obrisati kada se bude radila odbrana....

