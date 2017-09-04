CREATE TABLE receipts (
  id INT UNSIGNED AUTO_INCREMENT,
  uploaded TIME DEFAULT CURRENT_TIME(),
  merchant VARCHAR(255) NOT NULL,
  amount DECIMAL(12,2) NOT NULL,
  receipt_type INT UNSIGNED,

  PRIMARY KEY (id)
);


insert into receipts (merchant, amount, receipt_type) values ('best buy', 12, 1);

CREATE TABLE tags (
  id INT UNSIGNED AUTO_INCREMENT,
  receipt_id INT UNSIGNED,
  uploaded TIME DEFAULT CURRENT_TIME(),
  tag_category VARCHAR(255) NOT NULL,

  PRIMARY KEY (id),
  CONSTRAINT fk_receipts FOREIGN KEY (receipt_id)
  REFERENCES receipts(id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

insert into tags (receipt_id, tag_category) values (1, 'tech');


