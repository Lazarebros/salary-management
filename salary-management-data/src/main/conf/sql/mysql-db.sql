--
-- Table structure for tables
--

CREATE TABLE `companies` (
  `company_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (company_id)
);
ALTER TABLE `companies` ADD UNIQUE KEY `UC_Companies_Code` (`code`) USING BTREE;

CREATE TABLE `rates` (
  `rate_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `hourly` decimal(19,2) DEFAULT 0.00,
  `start_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `expected_gross` decimal(19,2) DEFAULT 0.00,
  `expected_net_pay` decimal(19,2) DEFAULT 0.00,
  PRIMARY KEY (rate_id),
  CONSTRAINT `fk_rate_company_id` FOREIGN KEY (`company_id`) references companies(`company_id`)
);
ALTER TABLE `rates` ADD UNIQUE KEY `UC_Rates` (`start_date`, `end_date`, `hourly`) USING BTREE;

CREATE TABLE `paychecks` (
  `paycheck_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `year` int(20) NOT NULL,
  `month` int(20) NOT NULL,
  `bi_week` int(20) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `number_of_hours` bigint(20) DEFAULT 0,
  `gross_amount` decimal(19,2) DEFAULT 0.00,
  `net_pay` decimal(19,2) DEFAULT 0.00,
  `reimbursement` decimal(19,2) DEFAULT 0.00,
  PRIMARY KEY (paycheck_id),
  CONSTRAINT `fk_paycheck_company_id` FOREIGN KEY (`company_id`) references companies(`company_id`)
);
ALTER TABLE `paychecks` ADD UNIQUE KEY `UC_Paychecks` (`company_id`,`start_date`,`end_date`) USING BTREE;

INSERT INTO `companies` (`code`, `name`) VALUES ('MMI', 'Mitchell Martin INC');
INSERT INTO `companies` (`code`, `name`) VALUES ('MS3', 'Mountain State Software Solutions LLC');

INSERT INTO rates (rate_id, company_id, hourly, start_date, end_date, expected_gross, expected_net_pay) VALUES (1, 1, 81.00, '2015-01-01', NULL, 6390.00, 4415.00);
INSERT INTO rates (rate_id, company_id, hourly, start_date, end_date, expected_gross, expected_net_pay) VALUES (2, 2, 100.00, '2015-01-01', NULL, 8000.00, 5600.00);

