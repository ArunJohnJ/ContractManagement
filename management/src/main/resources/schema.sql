CREATE TABLE IF NOT EXISTS customer (
  customerId BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  customerName VARCHAR(80) NOT NULL,
  accountNumber VARCHAR(20) NOT NULL,  
  customerCode VARCHAR(20) NOT NULL,
  billingContactPhoneNumber VARCHAR(20) NOT NULL,
  billingContactEmail VARCHAR(50) NOT NULL  
);

CREATE TABLE IF NOT EXISTS contract (
  contractId BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
  effectiveDate DATE NULL, 
  expirationDate DATE NULL,
  lineOfBusiness VARCHAR(20) NOT NULL,
  contractNumber VARCHAR(20) NOT NULL,
  claimType VARCHAR(20) NOT NULL,
  customerIdFk BIGINT
  
);

CREATE TABLE IF NOT EXISTS policy (
  policyId BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  policyNumber VARCHAR(80) NOT NULL,
  effectiveDate DATE NULL, 
  expirationDate DATE NULL,
  policyInsuranceCompany VARCHAR(50) NOT NULL,  
  policyLimit DECIMAL(15,2) NULL,
  occurenceLimitType VARCHAR(20) NOT NULL,
  remark VARCHAR(50) NOT NULL,
  contractIdFk BIGINT
  
);

ALTER TABLE contract ADD FOREIGN KEY (customerIdFk) REFERENCES customer(customerId);
ALTER TABLE policy ADD FOREIGN KEY (contractIdFk) REFERENCES contract(contractId);

create view allData as select contract.contractId,contract.contractNumber,contract.lineOfBusiness,contract.effectiveDate as contractEffectiveDate,contract.expirationDate as contractExpirationDate,contract.claimType,
customer.customerName,customer.accountNumber,customer.customerCode,customer.billingContactPhoneNumber,customer.billingContactEmail,
policy.policyNumber,policy.effectiveDate as policyEffectiveDate,policy.expirationDate as policyExpirationDate,policy.policyInsuranceCompany,policy.policyLimit,policy.occurenceLimitType,policy.remark
from contract 
left join customer on customer.customerId=contract.customerIdFk 
left join policy on policy.contractIdFk=contract.contractId;




