create table userapp(
					user_id int not null auto_increment,
                    user_name VARCHAR(60) not null,
                    user_email VARCHAR(30)not null,
                    user_password VARCHAR(30) not null,
					primary key (user_id)
                    );

create table community(
					comm_id int not null auto_increment,
                    comm_name VARCHAR(30) not null,
                    primary key(comm_id));

create table usercommunity(
						usercomm_id int not null auto_increment,
                        usercomm_user_id int not null,
                        usercomm_comm_id int not null,
                        primary key(usercomm_id));

create table communitybudget(
							commbud_id int not null auto_increment,
                            commbud_comm_id int not null,
                            commbud_bicate_id int not null,
                            primary key(commbud_id));

create table communitybillbudget(
								commbibud_id int not null auto_increment,
                                commbibud_commbud_id int not null,
                                commbibud_bi_id int not null,
                                primary key(commbibud_id));

create table bill(
				bi_id int not null auto_increment,
                bi_name VARCHAR(30) not null,
                primary key (bi_id));

create table userbillbudget(
							userbibud_id int not null auto_increment,
                            userbibud_bi_id int not null,
                            userbibud_userbud_id int not null,
                            primary key(userbibud_id));

create table userbudget(
						userbud_id int not null auto_increment,
                        userbud_user_id int not null,
                        primary key (userbud_id));

create table billcate(
					 bicate_id int not null auto_increment,
                     bicate_name VARCHAR(60),
                     primary key (bicate_id));

create table challengeruser(
						  challuser_id int not null auto_increment,
                          challuser_user_id int not null,
                          challuser_chall_id int not null,
                          primary key (challuser_id));

create table challenger(
						chall_id int not null auto_increment,
                        chall_name VARCHAR(30),
                        primary key(chall_id));

create table usercommunitybudget(
								usercommbud_id int not null auto_increment,
                                usercommbud_usercomm_id int not null,
                                usercommbud_commbud_id int not null,
                                primary key(usercommbud_id));

create table userbudcategories(
								userbudcate_id int not null auto_increment,
                                userbudcate_bicate_id int not null,
                                userbudcate_userbud_id int not null,
                                primary key(userbudcate_id));

alter table usercommunity
add constraint usercommunity_fk_userapp
foreign key(usercomm_user_id) references userapp(user_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table usercommunity
add constraint usercommunity_fk_community
foreign key(usercomm_comm_id) references community(comm_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table communitybudget
add constraint communitybudget_fk_community
foreign key (commbud_comm_id) references community(comm_id) 
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table communitybudget
add constraint communitybudget_fk_billcate
foreign key(commbud_bicate_id) references billcate(bicate_id) 
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table communitybillbudget
add constraint communitybillbudget_fk_communitybudget
foreign key (commbibud_commbud_id) references communitybudget(commbud_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table communitybillbudget
add constraint communitybillbudget_fk_bill
foreign key (commbibud_bi_id) references bill(bi_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table userbillbudget
add constraint userbillbudget_fk_bill
foreign key (userbibud_bi_id) references bill(bi_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table userbillbudget
add constraint userbillbudget_fk_userbudget
foreign key (userbibud_userbud_id) references userbudget(userbud_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table challengeruser
add constraint challengeruser_fk_userapp
foreign key(challuser_user_id) references userapp(user_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table challengeruser
add constraint challengeruser_fk_challenger
foreign key (challuser_chall_id) references challenger(chall_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table usercommunitybudget
add constraint usercommunitybudget_fk_usercommunity
foreign key(usercommbud_usercomm_id) references usercommunity(usercomm_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table usercommunitybudget
add constraint usercommunitybudget_fk_communitybudget
foreign key (usercommbud_commbud_id) references communitybudget(commbud_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table userbudcategories
add constraint userbudcategories_fk_billcate
foreign key (userbudcate_bicate_id) references billcate(bicate_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table userbudcategories
add constraint userbudcategories_fk_userbudget
foreign key (userbudcate_userbud_id) references userbudget(userbud_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;


