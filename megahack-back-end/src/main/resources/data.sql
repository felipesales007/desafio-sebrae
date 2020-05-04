INSERT INTO `user`
(id, email, name, password, enabled, email_verified, picture, uuid, permission, telefone, type)
VALUES(1, 'usuario@megahack.com', 'railson silva', '$2a$10$TZm/84DoIMQ7s0cuyzu/Segsg6VVMb0lzYk8dNAg4lnJbw382szyC', 1, 1, null, '65dcf0e8-2075-4ec1-84bd-73b8a54e9fa5', 'USUARIO', '71982576778', 3);
VALUES(7, 'usuario@megahack.com', 'Railson Silva', '$2a$10$TZm/84DoIMQ7s0cuyzu/Segsg6VVMb0lzYk8dNAg4lnJbw382szyC', 1, 1, 'https://blogdoims.com.br/wp-content/uploads/2016/61/1396826195.jpg', '65dcf0e8-2075-4ec1-84bd-73b8a54e9fa5', 'USUARIO', '71982576778', 3);

INSERT INTO `user`
(id, email, name, password, enabled, email_verified, picture, uuid, permission, telefone, type)
VALUES(2, 'vendedor@megahack.com', 'Pizaria do Jordan', '$2a$10$TZm/84DoIMQ7s0cuyzu/Segsg6VVMb0lzYk8dNAg4lnJbw382szyC', 1, 1, null, '547c247c-24a7-4246-b036-7504a040fe99', 'VENDEDOR', '71982576771', 1);
VALUES(6, 'seuze@megahack.com', 'Seu Zé Pamonheiro', '$2a$10$TZm/84DoIMQ7s0cuyzu/Segsg6VVMb0lzYk8dNAg4lnJbw382szyC', 1, 1, 'https://blogdoims.com.br/wp-content/uploads/2016/61/1396826195.jpg', '547c247c-24a7-4246-b036-7504a040fe99', 'VENDEDOR', '71982576771', 1);

INSERT INTO `user`
(id, email, name, password, enabled, email_verified, picture, uuid, permission, telefone, type)
VALUES(3, 'entregador@megahack.com', 'Pizzaria KiDelícia', '$2a$10$TZm/84DoIMQ7s0cuyzu/Segsg6VVMb0lzYk8dNAg4lnJbw382szyC', 1, 1, 'https://blogdoims.com.br/wp-content/uploads/2016/61/1396826195.jpg', 'b5621234-306a-4da1-bd0b-593134283512', 'ENTREGADOR', '71982576779', 2);
VALUES(5, 'manolo@megahack.com', 'Seu Manolo Laticínios', '$2a$10$TZm/84DoIMQ7s0cuyzu/Segsg6VVMb0lzYk8dNAg4lnJbw382szyC', 1, 1, null, 'b5621234-306a-4da1-bd0b-593134283512', 'VENDEDOR', '71982576779', 2);

INSERT INTO `user`
(id, email, name, password, enabled, email_verified, picture, uuid, permission, telefone, type)
VALUES(4, 'motoqueiro@megahack.com', 'Motoqueiro Silva', '$2a$10$TZm/84DoIMQ7s0cuyzu/Segsg6VVMb0lzYk8dNAg4lnJbw382szyC', 1, 1, 'https://blogdoims.com.br/wp-content/uploads/2016/61/1396826195.jpg', 'a02150d5-c54f-47d6-b0c6-a6f8f52cf6c5', 'ENTREGADOR', '71982576771', 2);


INSERT INTO chat
(id, chat_type, date_time, from_user, message, to_user)
values 
(1,'CHAT', '2020-04-30 05:53:43.347', '65dcf0e8-2075-4ec1-84bd-73b8a54e9fa5', 'oi','547c247c-24a7-4246-b036-7504a040fe99'),
(2,'CHAT', '2020-05-01 05:54:42.577', '65dcf0e8-2075-4ec1-84bd-73b8a54e9fa5', 'oi','547c247c-24a7-4246-b036-7504a040fe99'),
(3,'CHAT', '2020-05-01 06:03:03.898', '65dcf0e8-2075-4ec1-84bd-73b8a54e9fa5', 'alo','547c247c-24a7-4246-b036-7504a040fe99'),
(4,'CHAT', '2020-05-01 06:07:01.971', '65dcf0e8-2075-4ec1-84bd-73b8a54e9fa5', 'mano', '547c247c-24a7-4246-b036-7504a040fe99'),
(5,'CHAT', '2020-05-01 06:18:38.391', '65dcf0e8-2075-4ec1-84bd-73b8a54e9fa5', 'oi', 'b5621234-306a-4da1-bd0b-593134283512'),
(6,'CHAT', '2020-05-01 06:18:39.425', '65dcf0e8-2075-4ec1-84bd-73b8a54e9fa5', 'oi', 'b5621234-306a-4da1-bd0b-593134283512');

insert into `ADDRESS` (ID, STREET, ZIP_CODE, CITY, NEIGHBORHOOD, NUMBER, PARENTS, STATE, LATITUDE, LONGITUDE)  
values (1, 'Nova das Flores', '42233300','Salvador', 'Pernambues', 10, 'Brasil', 'Bahia','-12.9729972','-38.4656135');

insert into `ESTABLISHMENT` (ID, NAME, ADDRESS_ID,USER_ID)   
values(1, 'Padaria do seu Zé', 1,2);

insert into `ESTABLISHMENT` (ID, NAME, ADDRESS_ID,USER_ID, IMAGEURL)   
values(2, 'Pizaria KiDelícia', 1,3, 'https://megahacksebrae.s3.us-east-2.amazonaws.com/b5621234-306a-4da1-bd0b-5931342835122020-05-02T13%3A55%3A25.568.jpg');

insert into `PRODUCT` (ID,ACTIVE,NAME,PRICE,TYPE,ESTABLISHMENT_ID, DESCRIPTION, imageURL) values
(1,true , 'Cachorro quente', 2.50,'COMIDA', 1, 'Com duas salsichas e molho especial', null),
(2,true , 'Misto', 2.50,'COMIDA', 1, 'Queijo e preseunto', 'https://megahacksebrae.s3.us-east-2.amazonaws.com/b5621234-306a-4da1-bd0b-5931342835122020-05-02T14%3A12%3A44.362product.jpg'),
(3,true , 'Suco', 2.50,'BEBIDA', 1, 'Sabor da casa', null),
(4,true , 'Hambuger', 2.50,'COMIDA', 1, 'Carne vegana com vegetais da horta do zé', null),
(5,true , 'Cerveja', 2.50,'BEBIDA', 1, 'Cerveja artesanal feita no proprio bairro', null),
(6,true , 'Agua', 2.50,'BEBIDA', 1, 'Sem gás', null),
(7,true , 'Cha', 1.00,'BEBIDA', 1, 'Chá de capim grosso', null);