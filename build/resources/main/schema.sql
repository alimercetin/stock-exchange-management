create table STOCK(
  ID bigint not null AUTO_INCREMENT,
  NAME varchar(100) not null,
  DESCRIPTION varchar(500),
  CURRENT_PRICE numeric,
  LAST_UPDATE timestamp,
  PRIMARY KEY ( ID )
);

create table STOCK_EXCHANGE(
  ID bigint not null AUTO_INCREMENT,
  NAME varchar(100) not null UNIQUE,
  DESCRIPTION varchar(500),
  PRIMARY KEY ( ID )
);

create table STOCK_EXCHANGE_STOCKS(
  STOCK_EXCHANGE_ID bigint references STOCK_EXCHANGE(ID) on delete cascade,
  STOCK_ID bigint references STOCK(ID) on delete cascade,
  PRIMARY KEY ( STOCK_EXCHANGE_ID, STOCK_ID )
);