Insert Into PLAN_CATEGORY_MASTER(
    CATEGORY_ID,CATEGORY_NAME) values(
    1,'Food');

Insert Into PLAN_CATEGORY_MASTER(
    CATEGORY_ID,CATEGORY_NAME) values(
    2,'Child Care');

Insert Into PLAN_CATEGORY_MASTER(
    CATEGORY_ID,CATEGORY_NAME) values(
    3,'UnEmployment');

Insert Into PLAN_CATEGORY_MASTER(
    CATEGORY_ID,CATEGORY_NAME) values(
    4,'Health');

    
    
    
Insert Into APP_PLANS (
    PLAN_ID,PLAN_NAME,PLAN_START_DATE,PLAN_END_DATE,CATEGORY_ID,ACTIVE_SW) values(
    1,'SNAP','2021-09-20','2021-12-20',1,'Y');

Insert Into APP_PLANS (
    PLAN_ID,PLAN_NAME,PLAN_START_DATE,PLAN_END_DATE,CATEGORY_ID,ACTIVE_SW) values(
    2,'CCAP','2021-09-20','2021-12-20',2,'Y');

Insert Into APP_PLANS (
    PLAN_ID,PLAN_NAME,PLAN_START_DATE,PLAN_END_DATE,CATEGORY_ID,ACTIVE_SW) values(
    3,'NJW','2021-09-20','2021-12-20',3,'Y');

Insert Into APP_PLANS (
    PLAN_ID,PLAN_NAME,PLAN_START_DATE,PLAN_END_DATE,CATEGORY_ID,ACTIVE_SW) values(
    4,'Medicare','2021-09-20','2021-12-20',4,'Y');

    
    
    
Insert Into CW_ACCOUNTS (
    ACCT_ID,FULLNAME,EMAIL,MOBILE_NUM,GENDER,DOB,SSN,ACTIVE_SW) values(
    1,'karnan Sooriyakumar','karnan@gmail.com',0771027990,'M','2021-05-21',123456789,'Y');
    