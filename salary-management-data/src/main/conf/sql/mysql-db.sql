--
-- Table structure for tables
--

CREATE TABLE `companies` (
  `company_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,
  `expected_net_pay` decimal(19,2) DEFAULT 0.00,
  PRIMARY KEY (company_id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
ALTER TABLE `companies` ADD UNIQUE KEY `UC_Paycheck_Code` (`code`) USING BTREE;

INSERT INTO `companies` (`code`, `name`, `expected_net_pay`) VALUES ('MMI', 'Mitchell Martin INC', 4415.00);
INSERT INTO `companies` (`code`, `name`, `expected_net_pay`) VALUES ('MS3', 'Mountain State Software Solutions LLC', 5600.00);

CREATE TABLE `paychecks` (
  `paycheck_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_code` varchar(32) NOT NULL,
  `year` int(20) NOT NULL,
  `month` int(20) NOT NULL,
  `bi_week` int(20) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `number_of_hours` bigint(20) DEFAULT 0,
  `hourly_rate` decimal(19,2) DEFAULT 0.00,
  `gross_amount` decimal(19,2) DEFAULT 0.00,
  `net_pay` decimal(19,2) DEFAULT 0.00,
  `reimbursement` decimal(19,2) DEFAULT 0.00,
  PRIMARY KEY (paycheck_id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
ALTER TABLE `paychecks` ADD UNIQUE KEY `UC_Paycheck_Start_Date` (`start_date`) USING BTREE;
ALTER TABLE `paychecks` ADD UNIQUE KEY `UC_Paycheck_End_Date` (`end_date`) USING BTREE;
