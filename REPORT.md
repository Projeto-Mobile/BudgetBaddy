
CREATE TABLE userapp (
    user_id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(60) NOT NULL,
    user_email VARCHAR(30) NOT NULL,
    user_password VARCHAR(30) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE community (
    comm_id INT NOT NULL AUTO_INCREMENT,
    comm_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (comm_id)
);

CREATE TABLE usercommunity (
    usercomm_id INT NOT NULL AUTO_INCREMENT,
    usercomm_user_id INT NOT NULL,
    usercomm_comm_id INT NOT NULL,
    PRIMARY KEY (usercomm_id)
);

CREATE TABLE communitybudget (
    commbud_id INT NOT NULL AUTO_INCREMENT,
    commbud_comm_id INT NOT NULL,
    commbud_bicate_id INT NOT NULL,
    commbud_tipbud_id INT NOT NULL,
    PRIMARY KEY (commbud_id)
);

CREATE TABLE communitybillbudget (
    commbibud_id INT NOT NULL AUTO_INCREMENT,
    commbibud_commbud_id INT NOT NULL,
    commbibud_bi_id INT NOT NULL,
    PRIMARY KEY (commbibud_id)
);

CREATE TABLE bill (
    bi_id INT NOT NULL AUTO_INCREMENT,
    bi_name VARCHAR(30) NOT NULL,
    bi_num INT,
    PRIMARY KEY (bi_id)
);

CREATE TABLE userbillbudget (
    userbibud_id INT NOT NULL AUTO_INCREMENT,
    userbibud_bi_id INT NOT NULL,
    userbibud_user_id INT NOT NULL,
    userbibud_tipbud_id INT NOT NULL,
    PRIMARY KEY (userbibud_id)
);

CREATE TABLE userbudget (
    userbud_id INT NOT NULL AUTO_INCREMENT,
    userbud_user_id INT NOT NULL,
    userbud_bicate_id INT NOT NULL,
    userbud_tipbud_id INT NOT NULL,
    PRIMARY KEY (userbud_id)
);

CREATE TABLE billcate (
    bicate_id INT NOT NULL AUTO_INCREMENT,
    bicate_name VARCHAR(60) NOT NULL,
    bicate_num INT,
    PRIMARY KEY (bicate_id)
);

CREATE TABLE challengeruser (
    challuser_id INT NOT NULL AUTO_INCREMENT,
    challuser_user_id INT NOT NULL,
    challuser_chall_id INT NOT NULL,
    PRIMARY KEY (challuser_id)
);

CREATE TABLE challenger (
    chall_id INT NOT NULL AUTO_INCREMENT,
    chall_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (chall_id)
);

CREATE TABLE usercommunitybudget (
    usercommbud_id INT NOT NULL AUTO_INCREMENT,
    usercommbud_usercomm_id INT NOT NULL,
    usercommbud_commbud_id INT NOT NULL,
    PRIMARY KEY (usercommbud_id)
);

CREATE TABLE tipobudget (
    tipbud_id INT NOT NULL AUTO_INCREMENT,
    tipbud_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (tipbud_id)
);

ALTER TABLE usercommunity
    ADD CONSTRAINT usercommunity_fk_userapp
    FOREIGN KEY (usercomm_user_id) REFERENCES userapp(user_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE usercommunity
    ADD CONSTRAINT usercommunity_fk_community
    FOREIGN KEY (usercomm_comm_id) REFERENCES community(comm_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE communitybudget
    ADD CONSTRAINT communitybudget_fk_community
    FOREIGN KEY (commbud_comm_id) REFERENCES community(comm_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE communitybudget
    ADD CONSTRAINT communitybudget_fk_billcate
    FOREIGN KEY (commbud_bicate_id) REFERENCES billcate(bicate_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE communitybudget
    ADD CONSTRAINT communitybudget_fk_tipobudget
    FOREIGN KEY (commbud_tipbud_id) REFERENCES tipobudget(tipbud_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE communitybillbudget
    ADD CONSTRAINT communitybillbudget_fk_communitybudget
    FOREIGN KEY (commbibud_commbud_id) REFERENCES communitybudget(commbud_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE communitybillbudget
    ADD CONSTRAINT communitybillbudget_fk_bill
    FOREIGN KEY (commbibud_bi_id) REFERENCES bill(bi_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE userbillbudget
    ADD CONSTRAINT userbillbudget_fk_bill
    FOREIGN KEY (userbibud_bi_id) REFERENCES bill(bi_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE userbillbudget
    ADD CONSTRAINT userbillbudget_fk_userapp
    FOREIGN KEY (userbibud_user_id) REFERENCES userapp(user_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE userbillbudget
    ADD CONSTRAINT userbillbudget_fk_tipobudget
    FOREIGN KEY (userbibud_tipbud_id) REFERENCES tipobudget(tipbud_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE challengeruser
    ADD CONSTRAINT challengeruser_fk_userapp
    FOREIGN KEY (challuser_user_id) REFERENCES userapp(user_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE challengeruser
    ADD CONSTRAINT challengeruser_fk_challenger
    FOREIGN KEY (challuser_chall_id) REFERENCES challenger(chall_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE usercommunitybudget
    ADD CONSTRAINT usercommunitybudget_fk_usercommunity
    FOREIGN KEY (usercommbud_usercomm_id) REFERENCES usercommunity(usercomm_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE usercommunitybudget
    ADD CONSTRAINT usercommunitybudget_fk_communitybudget
    FOREIGN KEY (usercommbud_commbud_id) REFERENCES communitybudget(commbud_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE userbudget
    ADD CONSTRAINT userbudget_fk_userapp
    FOREIGN KEY (userbud_user_id) REFERENCES userapp(user_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE userbudget
    ADD CONSTRAINT userbudget_fk_billcate
    FOREIGN KEY (userbud_bicate_id) REFERENCES billcate(bicate_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE userbudget
    ADD CONSTRAINT userbudget_fk_tipobudget
    FOREIGN KEY (userbud_tipbud_id) REFERENCES tipobudget(tipbud_id)
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

