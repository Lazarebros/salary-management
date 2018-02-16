--
-- Table structure for tables
--

DROP TABLE IF EXISTS paychecks;
DROP TABLE IF EXISTS rates;
DROP TABLE IF EXISTS companies;

CREATE TABLE IF NOT EXISTS companies (
  company_id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(32) NOT NULL,
  name varchar(64) NOT NULL,
  PRIMARY KEY (company_id),
  CONSTRAINT UC_Companies_Code UNIQUE (code)
);

CREATE TABLE IF NOT EXISTS rates (
  rate_id bigint(20) NOT NULL AUTO_INCREMENT,
  company_id bigint(20) NOT NULL,
  hourly_rate decimal(19,2) DEFAULT 0.00,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  expected_gross decimal(19,2) DEFAULT 0.00,
  expected_net_pay decimal(19,2) DEFAULT 0.00,
  PRIMARY KEY (rate_id),
  CONSTRAINT fk_rate_company_id FOREIGN KEY (company_id) references companies(company_id),
  CONSTRAINT UC_Rates UNIQUE (company_id, start_date, end_date, hourly_rate)
);

CREATE TABLE IF NOT EXISTS paychecks (
  paycheck_id bigint(20) NOT NULL AUTO_INCREMENT,
  company_id bigint(20) NOT NULL,
  year int(20) NOT NULL,
  month int(20) NOT NULL,
  bi_week int(20) NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime NOT NULL,
  number_of_hours bigint(20) DEFAULT 0,
  gross_amount decimal(19,2) DEFAULT 0.00,
  net_pay decimal(19,2) DEFAULT 0.00,
  reimbursement decimal(19,2) DEFAULT 0.00,
  PRIMARY KEY (paycheck_id),
  CONSTRAINT fk_paycheck_company_id FOREIGN KEY (company_id) references companies(company_id),
  CONSTRAINT UC_Paychecks UNIQUE (company_id,start_date,end_date)
);
