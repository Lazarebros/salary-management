--
-- Table structure for table `paycheck`
--

CREATE TABLE `paycheck` (
  `paycheck_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(32) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `number_of_hours` bigint(20) DEFAULT 0,
  `hourly_rate` decimal(19,2) DEFAULT 0.00,
  `gross_amout` decimal(19,2) DEFAULT 0.00,
  `net_pay` decimal(19,2) DEFAULT 0.00,
  `reimbursement` decimal(19,2) DEFAULT 0.00,
  PRIMARY KEY (paycheck_id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
ALTER TABLE `paycheck` ADD UNIQUE KEY `UC_Paycheck_Start_Date` (`start_date`) USING BTREE;
ALTER TABLE `paycheck` ADD UNIQUE KEY `UC_Paycheck_End_Date` (`end_date`) USING BTREE;
