use spg;

commit;

CREATE TABLE cardholders (
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  optlock int unsigned DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

commit;

CREATE TABLE cards (
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  pan varchar(32) NOT NULL,
  expiry varchar(12) NOT NULL,
  /* cvv field not persisted */
  optlock int unsigned DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

commit;

CREATE TABLE payments (
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  invoice varchar(100) NOT NULL,
  amount varchar(100) NOT NULL,
  currency varchar(10) NOT NULL,
  card_holder bigint unsigned,
  card bigint unsigned,
  optlock int unsigned DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

commit;

alter table invoices add foreign key (cardholder)
REFERENCES cardholders(id) ON DELETE SET NULL ON UPDATE CASCADE;
alter table invoices add foreign key (cards)
REFERENCES card(id) ON DELETE SET NULL ON UPDATE CASCADE;
