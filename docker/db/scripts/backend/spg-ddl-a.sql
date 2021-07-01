use spg;

commit;

CREATE TABLE payments (
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  invoice varchar(100) NOT NULL,
  amount varchar(100) NOT NULL,
  currency varchar(10) NOT NULL,
  name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  pan varchar(32) NOT NULL,
  expiry varchar(12) NOT NULL,
  /* cvv field not persisted */
  optlock int unsigned DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

commit;
