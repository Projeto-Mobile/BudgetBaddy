create table userapp(
					user_id int not null auto_increment,
                    user_name VARCHAR(60) not null,
                    user_email VARCHAR(30)not null,
                    user_password VARCHAR(30) not null,
					primary key (user_id));

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
                            commbud_tipbud_id int not null,
                            primary key(commbud_id));

create table communitybillbudget(
								commbibud_id int not null auto_increment,
                                commbibud_commbud_id int not null,
                                commbibud_bi_id int not null,
                                primary key(commbibud_id));

create table bill(
				bi_id int not null auto_increment,
                bi_name VARCHAR(30) not null,
                bi_num int,
                primary key (bi_id));

create table userbillbudget(
							userbibud_id int not null auto_increment,
                            userbibud_bi_id int not null,
                            userbibud_user_id int not null,
                            userbibud_tipbud_id int not null,
                            primary key(userbibud_id));

create table userbudget(
						userbud_id int not null auto_increment,
                        userbud_user_id int not null,
                        userbud_bicate_id int not null,
                        userbud_tipbud_id int not null,
                        primary key (userbud_id));

create table billcate(
					 bicate_id int not null auto_increment,
                     bicate_name VARCHAR(60) not null,
                     bicate_num int,
                     primary key (bicate_id));

create table challengeruser(
						  challuser_id int not null auto_increment,
                          challuser_user_id int not null,
                          challuser_chall_id int not null,
                          primary key (challuser_id));

create table challenger(
						chall_id int not null auto_increment,
                        chall_name VARCHAR(30) not null,
                        primary key(chall_id));

create table usercommunitybudget(
								usercommbud_id int not null auto_increment,
                                usercommbud_usercomm_id int not null,
                                usercommbud_commbud_id int not null,
                                primary key(usercommbud_id));
create table tipobudget(
						tipbud_id int not null auto_increment,
                        tipbud_name VARCHAR(30) not null,
                        primary  key (tipbud_id));

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

alter table communitybudget
add constraint communitybudget_fk_tipobudget
foreign key (commbud_tipbud_id) references tipobudget(tipbud_id)
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
add constraint userbillbudget_fk_userapp
foreign key (userbibud_user_id) references userapp(user_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table userbillbudget
add constraint userbillbudget_fk_tipobudget
foreign key (userbibud_tipbud_id) references tipobudget(tipbud_id)
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

alter table userbudget
add constraint userbudget_fk_userapp
foreign key(userbud_user_id) references userapp(user_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table userbudget
add constraint userbudget_fk_billcate
foreign key(userbud_bicate_id) references billcate(bicate_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table userbudget
add constraint userbudget_fk_tipobudget
foreign key (userbud_tipbud_id) references tipobudget(tipbud_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;



## Dicionário - BudgetBuddy

### Tabela userapp

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| user_id          | INT        | Not null, auto_increment |
| user_name        | VARCHAR(60) | Not null              |
| user_email       | VARCHAR(30) | Not null              |
| user_password    | VARCHAR(30) | Not null              |

### Tabela community

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| comm_id          | INT        | Not null, auto_increment |
| comm_name        | VARCHAR(30) | Not null              |

### Tabela usercommunity

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| usercomm_id      | INT        | Not null, auto_increment |
| usercomm_user_id |            | Not null              |
| usercomm_comm_id |            | Not null              |

### Tabela communitybudget

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| commbud_id       | INT        | Not null, auto_increment |
| commbud_comm_id  |            | Not null              |
| commbud_bicate_id|            | Not null              |
| commbud_tipbud_id|            | Not null              |

### Tabela communitybillbudget

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| commbibud_id     | INT        | Not null, auto_increment |
| commbibud_commbud_id |       | Not null              |
| commbibud_bi_id  |            | Not null              |

### Tabela bill

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| bi_id            | INT        | Not null, auto_increment |
| bi_name          | VARCHAR(30) | Not null              |
| bi_num           | INT        |                       |

### Tabela userbillbudget

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| userbibud_id     | INT        | Not null, auto_increment |
| userbibud_bi_id  |            | Not null              |
| userbibud_user_id|            | Not null              |
| userbibud_tipbud_id|           | Not null              |

### Tabela userbudget

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| userbud_id       | INT        | Not null, auto_increment |
| userbud_user_id  |            | Not null              |
| userbud_bicate_id|            | Not null              |
| userbud_tipbud_id|            | Not null              |

### Tabela billcate

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| bicate_id        | INT        | Not null, auto_increment |
| bicate_name      | VARCHAR(60) | Not null              |
| Bicate_num       | INT        |                       |

### Tabela challengeruser

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| challuser_id     | INT        | Not null, auto_increment |
| challuser_user_id|            | Not null              |
| challuser_chall_id|            | Not null              |

### Tabela challenger

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| chall_id         | INT        | Not null, auto_increment |
| chall_name       | VARCHAR(30) | Not null              |

### Tabela usercommunitybudget

| Coluna           | Tipo       | Restrições            |
|------------------|------------|-----------------------|
| usercommbud_id   | INT        | Not null, auto_increment |
| usercommbud_usercomm_id|       | Not null              |
| usercommbud_commbud_id|        | Not null              |


### Restrições e Relacionamentos

#### Tabela "usercommunity"
- **usercomm_user_id:** Chave que referencia `userapp` (`user_id`).
- **usercomm_comm_id:** Chave que referencia `community` (`comm_id`).

#### Tabela "communitybudget"
- **commbud_comm_id:** Chave que referencia `community` (`comm_id`).
- **commbud_bicate_id:** Chave que referencia `billcate` (`bicate_id`).
- **commbud_tipbud_id:** Chave que referencia `tipobudget` (`tipbud_id`).

#### Tabela "communitybillbudget"
- **commbibud_commbud_id:** Chave que referencia `community` (`commbud_id`).
- **commbibud_bi_id:** Chave que referencia `bill` (`bi_id`).

#### Tabela "userbillbudget"
- **userbibud_user_id:** Chave que referencia `userapp` (`user_id`).
- **userbibud_bi_id:** Chave que referencia `bill` (`bi_id`).
- **userbibud_tipbud_id:** Chave que referencia `tipobudget` (`tipbud_id`).

#### Tabela "userbudget"
- **userbud_user_id:** Chave que referencia `userapp` (`user_id`).
- **userbud_bicate_id:** Chave que referencia `billcate` (`bicate_id`).
- **userbud_tipbud_id:** Chave que referencia `tipobudget` (`tipbud_id`).

#### Tabela "challengeruser"
- **challuser_user_id:** Chave que referencia `userapp` (`user_id`).
- **challuser_chall_id:** Chave que referencia `challenger` (`chall_id`).

#### Tabela "usercommunitybudget"
- **usercommbud_usercomm_id:** Chave que referencia `usercommunity` (`usercomm_id`).
- **usercommbud_commbud_id:** Chave que referencia `communitybudget` (`commbud_id`).


![Imagem WhatsApp 2023-11-25 às 15 30 36_75d3a072](https://github.com/Projeto-Mobile/BudgetBuddy/assets/115008459/a8ae6064-2df1-4696-85cc-e9cfcec39e37)

