/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2014/7/11 16:26:53                           */
/*==============================================================*/


alter table T_BID_WIN
   drop constraint FK_T_BID_WI_REFERENCE_T_KILL_I;

alter table T_BID_WIN
   drop constraint FK_T_BID_WI_REFERENCE_T_PRODUC;

alter table T_CAR_STYLE
   drop constraint FK_T_CAR_ST_REFERENCE_T_PRODUC;

alter table T_CLT
   drop constraint FK_T_CLT_REFERENCE_T_DEPT;

alter table T_DRIVER_COST
   drop constraint FK_T_DRIVER_REFERENCE_T_TRUCK_;

alter table T_FINANCE
   drop constraint FK_T_FINANC_REFERENCE_T_COMPAN;

alter table T_KILL_DETAIL
   drop constraint FK_T_KILL_D_REFERENCE_T_KILL_I;

alter table T_KILL_DETAIL
   drop constraint FK_T_KILL_D_REFERENCE_T_CAR_ST;

alter table T_KILL_INFO
   drop constraint FK_T_KILL_I_REFERENCE_T_PRODUC;

alter table T_LOAN_ASK
   drop constraint FK_T_LOAN_A_REFERENCE_T_LOAN;

alter table T_LOAN_ASK
   drop constraint FK_T_LOAN_A_REFERENCE_T_LOAN_H;

alter table T_LOAN_HANDLE
   drop constraint FK_T_LOAN_H_REFERENCE_T_LOAN;

alter table T_ORDER
   drop constraint FK_T_ORDER_REFERENCE_T_SUB_CA;

alter table T_ORDER
   drop constraint FK_T_ORDER_REFERENCE_T_USER;

alter table T_PRODUCT
   drop constraint FK_T_PRODUC_REFERENCE_T_USER;

alter table T_RESOURCE_ARCHIVE
   drop constraint FK_T_RESOUR_REFERENCE_T_ARCHIV;

alter table T_RESOURCE_ARCHIVE
   drop constraint FK_T_RESOUR_REFERENCE_T_RESOUR;

alter table T_ROLE_RESOURCE
   drop constraint FK_T_ROLE_R_REFERENCE_T_ROLE;

alter table T_ROLE_RESOURCE
   drop constraint FK_T_ROLE_R_REFERENCE_T_RESOUR;

alter table T_SHIPHEAD
   drop constraint FK_T_SHIPHE_REFERENCE_T_TRUCK_;

alter table T_SHIPLINE
   drop constraint FK_T_SHIPLI_REFERENCE_T_ORDER;

alter table T_SHIPLINE
   drop constraint FK_T_SHIPLI_REFERENCE_T_SHIPHE;

alter table T_USER
   drop constraint FK_T_USER_REFERENCE_T_FINANC;

alter table T_USER
   drop constraint FK_T_USER_REFERENCE_T_SUBSUP;

alter table T_USER
   drop constraint FK_T_USER_REFERENCE_T_CLT;

alter table T_USER
   drop constraint FK_T_USER_REFERENCE_T_ARCHIV;

alter table T_USER_ROLE
   drop constraint FK_T_USER_R_REFERENCE_T_USER;

alter table T_USER_ROLE
   drop constraint FK_T_USER_R_REFERENCE_T_ROLE;

drop table T_ARCHIVE_TYPE cascade constraints;

drop table T_ARKILOMETER cascade constraints;

drop table T_ARORDER cascade constraints;

drop table T_BID_WIN cascade constraints;

drop table T_CAR_STYLE cascade constraints;

drop table T_CLT cascade constraints;

drop table T_COMPANY_TYPE cascade constraints;

drop table T_DEPT cascade constraints;

drop table T_DRIVER_COST cascade constraints;

drop table T_FINANCE cascade constraints;

drop table T_KILL_DETAIL cascade constraints;

drop table T_KILL_INFO cascade constraints;

drop table T_LOAN cascade constraints;

drop table T_LOAN_ASK cascade constraints;

drop table T_LOAN_HANDLE cascade constraints;

drop table T_MANGE_SCOPE cascade constraints;

drop table T_MESSAGE cascade constraints;

drop table T_ORDER cascade constraints;

drop table T_PRODUCT cascade constraints;

drop table T_RESOURCE cascade constraints;

drop table T_RESOURCE_ARCHIVE cascade constraints;

drop table T_ROLE cascade constraints;

drop table T_ROLE_RESOURCE cascade constraints;

drop table T_SHIPHEAD cascade constraints;

drop table T_SHIPLINE cascade constraints;

drop table T_SUBSUPPLIERS cascade constraints;

drop table T_SUB_CAR_STYLE cascade constraints;

drop table T_TRUCK_DRIVER cascade constraints;

drop table T_USER cascade constraints;

drop table T_USER_ROLE cascade constraints;

/*==============================================================*/
/* Table: T_ARCHIVE_TYPE                                        */
/*==============================================================*/
create table T_ARCHIVE_TYPE 
(
   ID                   NUMBER               not null,
   VC_ARCHIVE           ARCHIVE,
   N_ENABLE             NUMBER               default 0,
   vc_tablename 		varchar2(100) 			null,
   constraint PK_T_ARCHIVE_TYPE primary key (ID)
);

comment on table T_ARCHIVE_TYPE is
'档案类型表';

comment on column T_ARCHIVE_TYPE.ID is
'主键';

comment on column T_ARCHIVE_TYPE.VC_ARCHIVE is
'对应档案名';

comment on column T_ARCHIVE_TYPE.vc_tablename is
'对应档案表名';

comment on column T_ARCHIVE_TYPE.N_ENABLE is
'是否有效（0有效，1无效）';

/*==============================================================*/
/* Table: T_ARKILOMETER                                         */
/*==============================================================*/
create table T_ARKILOMETER 
(
   ID                   NUMBER               not null,
   I_START_ID           NUMBER,
   I_END_ID             NUMBER,
   N_KILOMETER          NUMBER,
   VC_START_CITY        VARCHAR(50),
   VC_END_CITY          VARCHAR(50),
   DT_START             DATE,
   DT_END               DATE,
   DT_UPDATE            DATE,
   I_UPDATE_USER        NUMBER,
   N_ENABLE             NUMBER               default 0,
   VC_SUBNO             VARCHAR(32),
   constraint PK_T_ARKILOMETER primary key (ID)
);

comment on table T_ARKILOMETER is
'应收公里数';

comment on column T_ARKILOMETER.ID is
'主键';

comment on column T_ARKILOMETER.I_START_ID is
'起始地序号';

comment on column T_ARKILOMETER.I_END_ID is
'目的地序号';

comment on column T_ARKILOMETER.N_KILOMETER is
'公里数';

comment on column T_ARKILOMETER.VC_START_CITY is
'起始地';

comment on column T_ARKILOMETER.VC_END_CITY is
'目的地';

comment on column T_ARKILOMETER.DT_START is
'开始日期';

comment on column T_ARKILOMETER.DT_END is
'结束日期';

comment on column T_ARKILOMETER.DT_UPDATE is
'最后更新时间';

comment on column T_ARKILOMETER.I_UPDATE_USER is
'最后一次维护人';

comment on column T_ARKILOMETER.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_ARKILOMETER.VC_SUBNO is
'所属分供方编号';

/*==============================================================*/
/* Table: T_ARORDER                                             */
/*==============================================================*/
create table T_ARORDER 
(
   ID                   NUMBER               not null,
   I_ORDER              NUMBER,
   I_SHIPHEAD			NUMBER,
   I_START              NUMBER,
   I_SHIPLINE			NUMBER,
   VC_START_CITY        VARCHAR(100),
   I_DEST               NUMBER,
   VC_DEST_CITY         VARCHAR(100),
   I_CAR_STYLE          NUMBER,
   VC_CAR_STYLE         VARCHAR(50),
   N_PRICE              NUMBER,
   N_ARKILOMETER        NUMBER,
   N_QTY                NUMBER,
   DT_SHIP              DATE,
   DT_COME              DATE,
   N_AUDIT				number             default 1,
   DT_AUDIT             DATE,
   VC_AUDIT_NAME        VARCHAR(20),
   I_AUDIT_USER         NUMBER,
   N_CREATE_BILL        NUMBER,
   DT_CREATE_BILL       DATE,
   I_BILL_USER          NUMBER,
   I_BILL_NO            NUMBER,
   N_BILL               NUMBER,
   DT_BILL              DATE,
   I_CHECK_USER         NUMBER,
   N_ENABLE             NUMBER               default 0,
   constraint PK_T_ARORDER primary key (ID)
);

comment on table T_ARORDER is
'结算应收';

comment on column T_ARORDER.ID is
'主键';

comment on column T_ARORDER.I_ORDER is
'订单号';

comment on column T_ARORDER.I_SHIPHEAD is
'发运指令表外键';

comment on column T_ARORDER.I_SHIPLINE is
'发运明细表外键';

comment on column T_ARORDER.I_START is
'起运地id';

comment on column T_ARORDER.VC_START_CITY is
'起运地';

comment on column T_ARORDER.I_DEST is
'目的地id';

comment on column T_ARORDER.VC_DEST_CITY is
'目的地';

comment on column T_ARORDER.I_CAR_STYLE is
'车型id';

comment on column T_ARORDER.VC_CAR_STYLE is
'车型';

comment on column T_ARORDER.N_PRICE is
'应收单价';

comment on column T_ARORDER.N_ARKILOMETER is
'应收公里';

comment on column T_ARORDER.N_QTY is
'数量';

comment on column T_ARORDER.DT_SHIP is
'最初起运时间';

comment on column T_ARORDER.DT_COME is
'最后抵达时间';

comment on column T_ARORDER.N_AUDIT is
'是否审核 (0已审核 1未审核)';

comment on column T_ARORDER.DT_AUDIT is
'审核时间';

comment on column T_ARORDER.VC_AUDIT_NAME is
'审核姓名';

comment on column T_ARORDER.I_AUDIT_USER is
'审核人id';

comment on column T_ARORDER.N_CREATE_BILL is
'是否生成对帐单';

comment on column T_ARORDER.DT_CREATE_BILL is
'帐单时间';

comment on column T_ARORDER.I_BILL_USER is
'帐单人';

comment on column T_ARORDER.I_BILL_NO is
'帐单号';

comment on column T_ARORDER.N_BILL is
'是否对帐';

comment on column T_ARORDER.DT_BILL is
'对帐时间';

comment on column T_ARORDER.I_CHECK_USER is
'对帐人';

comment on column T_ARORDER.N_ENABLE is
'是否有效（0有效，1无效）';

/*==============================================================*/
/* Table: T_BID_WIN                                             */
/*==============================================================*/
create table T_BID_WIN 
(
   ID                   NUMBER               not null,
   I_PRODUCT            NUMBER,
   I_USER               NUMBER,
   I_USER_NAME          VARCHAR(20),
   DT_BID               DATE                 default SYSDATE,
   N_ENABLE             NUMBER               default 0,
   I_KILL_INFO          NUMBER,
   I_CONFIRM            NUMBER,
   VC_CONFIRM_NAME      VARCHAR(30),
   DT_CONFIRM           DATE,
   VC_CONFIRM           VARCHAR(400),
   constraint PK_T_BID_WIN primary key (ID)
);

comment on table T_BID_WIN is
'中标表';

comment on column T_BID_WIN.ID is
'主键';

comment on column T_BID_WIN.I_PRODUCT is
'产品id';

comment on column T_BID_WIN.I_USER is
'中标人id';

comment on column T_BID_WIN.I_USER_NAME is
'中标人姓名';

comment on column T_BID_WIN.DT_BID is
'中标时间';

comment on column T_BID_WIN.N_ENABLE is
'状态（0有效，1无效）';

comment on column T_BID_WIN.I_KILL_INFO is
'秒杀信息表id';

comment on column T_BID_WIN.I_CONFIRM is
'确认人id';

comment on column T_BID_WIN.VC_CONFIRM_NAME is
'确认人姓名';

comment on column T_BID_WIN.DT_CONFIRM is
'确认时间';

comment on column T_BID_WIN.VC_CONFIRM is
'确认原因（备注）';

/*==============================================================*/
/* Table: T_CAR_STYLE                                           */
/*==============================================================*/
create table T_CAR_STYLE 
(
   ID                   NUMBER               not null,
   VC_CAR_STYLE         VARCHAR(20),
   N_CAR_COUNT          NUMBER,
   I_PRODUCT            NUMBER,
   N_ENABLE             NUMBER               default 0,
   N_PRICE              NUMBER,
   VC_NOTE              VARCHAR(400),
   constraint PK_T_CAR_STYLE primary key (ID)
);

comment on table T_CAR_STYLE is
'抢单车型信息';

comment on column T_CAR_STYLE.ID is
'主键';

comment on column T_CAR_STYLE.VC_CAR_STYLE is
'车型';

comment on column T_CAR_STYLE.N_CAR_COUNT is
'数量';

comment on column T_CAR_STYLE.I_PRODUCT is
'产品id';

comment on column T_CAR_STYLE.N_ENABLE is
'状态（0为有效，1为无效）';

comment on column T_CAR_STYLE.N_PRICE is
'车型单价';

comment on column T_CAR_STYLE.VC_NOTE is
'备注';

/*==============================================================*/
/* Table: T_CLT                                                 */
/*==============================================================*/
create table T_CLT 
(
   ID                   NUMBER               not null,
   VC_NAME              VARCHAR(50),
   I_DEPT               NUMBER,
   N_ENABLE             NUMBER               default 0,
   constraint PK_T_CLT primary key (ID)
);

comment on table T_CLT is
'管理员档案';

comment on column T_CLT.ID is
'主键';

comment on column T_CLT.VC_NAME is
'姓名';

comment on column T_CLT.I_DEPT is
'部门id';

comment on column T_CLT.N_ENABLE is
'是否有效（0有效，1无效）';

/*==============================================================*/
/* Table: T_COMPANY_TYPE                                        */
/*==============================================================*/
create table T_COMPANY_TYPE 
(
   ID                   NUMBER               not null,
   VC_TYPE              VARCHAR(50),
   N_ENABLE             NUMBER               default 0,
   constraint PK_T_COMPANY_TYPE primary key (ID)
);

comment on table T_COMPANY_TYPE is
'金融公司类型表';

comment on column T_COMPANY_TYPE.ID is
'主键';

comment on column T_COMPANY_TYPE.VC_TYPE is
'类型名称';

comment on column T_COMPANY_TYPE.N_ENABLE is
'是否有效（0有效，1无效）';

/*==============================================================*/
/* Table: T_DEPT                                                */
/*==============================================================*/
create table T_DEPT 
(
   ID                   NUMBER               not null,
   VC_NAME              VARCHAR(60),
   I_DEPT               NUMBER,
   N_ENABLE             NUMBER               default 0,
   N_SORT				NUMBER,
   constraint PK_T_DEPT primary key (ID)
);

comment on table T_DEPT is
'内部部门表';

comment on column T_DEPT.ID is
'主键';

comment on column T_DEPT.VC_NAME is
'部门名称';

comment on column T_DEPT.I_DEPT is
'父id';

comment on column T_DEPT.N_SORT is
'排序';

comment on column T_DEPT.N_ENABLE is
'是否有效（0有效，1无效）';

/*==============================================================*/
/* Table: T_DRIVER_COST                                         */
/*==============================================================*/
create table T_DRIVER_COST 
(
   ID                   NUMBER               not null,
   I_TRUCK_ID           NUMBER,
   DT_START             DATE,
   DT_END               DATE,
   N_COST               NUMBER,
   DT_ADD               DATE,
   N_ENABLE             NUMBER               default 0,
   VC_SUBNO             VARCHAR(32),
   I_UPDATE_USER        CHAR(10),
   constraint PK_T_DRIVER_COST primary key (ID)
);

comment on table T_DRIVER_COST is
'应收单价';

comment on column T_DRIVER_COST.ID is
'主键';

comment on column T_DRIVER_COST.I_TRUCK_ID is
'运输车id';

comment on column T_DRIVER_COST.DT_START is
'开始时间';

comment on column T_DRIVER_COST.DT_END is
'结束时间';

comment on column T_DRIVER_COST.N_COST is
'公里成本';

comment on column T_DRIVER_COST.DT_ADD is
'最后一次维护时间';

comment on column T_DRIVER_COST.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_DRIVER_COST.VC_SUBNO is
'所属分供方编号';

comment on column T_DRIVER_COST.I_UPDATE_USER is
'最后一次维护人';

/*==============================================================*/
/* Table: T_FINANCE                                             */
/*==============================================================*/
create table T_FINANCE 
(
   ID                   NUMBER               not null,
   VC_ALL_NAME          VARCHAR(100),
   VC_SHORT_NAME        VARCHAR(50),
   VC_ADDRESS           VARCHAR(100),
   VC_PROVINCE          VARCHAR(50),
   VC_CITY              VARCHAR(60),
   VC_AREA              VARCHAR(150),
   VC_DETAILED_ADDRESS  VARCHAR(150),
   N_APPLY              NUMBER               default 1,
   VC_APPLY_FILE        VARCHAR(50),
   N_ENABLE             NUMBER               default 0,
   I_TYPE               NUMBER,
   VC_FINANCENO         VARCHAR(32),
   constraint PK_T_FINANCE primary key (ID)
);

comment on table T_FINANCE is
'金融公司档案';

comment on column T_FINANCE.ID is
'主键';

comment on column T_FINANCE.VC_ALL_NAME is
'金融公司全称';

comment on column T_FINANCE.VC_SHORT_NAME is
'金融公司简称';

comment on column T_FINANCE.VC_ADDRESS is
'金融公司地址';

comment on column T_FINANCE.VC_PROVINCE is
'所属省份';

comment on column T_FINANCE.VC_CITY is
'所属城市';

comment on column T_FINANCE.VC_AREA is
'所属地区';

comment on column T_FINANCE.VC_DETAILED_ADDRESS is
'详细地址';

comment on column T_FINANCE.N_APPLY is
'是否可贷款（0可，1不可，默认为1）';

comment on column T_FINANCE.VC_APPLY_FILE is
'贷款证明';

comment on column T_FINANCE.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_FINANCE.I_TYPE is
'公司类型';

comment on column T_FINANCE.VC_FINANCENO is
'金融公司编号';

/*==============================================================*/
/* Table: T_KILL_DETAIL                                         */
/*==============================================================*/
create table T_KILL_DETAIL 
(
   ID                   NUMBER               not null,
   I_KILL_INFO          NUMBER,
   N_ENABLE             NUMBER               default 0,
   I_CAR_STYLE          NUMBER,
   N_PRICE              NUMBER,
   constraint PK_T_KILL_DETAIL primary key (ID)
);

comment on table T_KILL_DETAIL is
'秒杀明细表';

comment on column T_KILL_DETAIL.ID is
'主键';

comment on column T_KILL_DETAIL.I_KILL_INFO is
'秒杀表主键';

comment on column T_KILL_DETAIL.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_KILL_DETAIL.I_CAR_STYLE is
'抢单车型信息id';

comment on column T_KILL_DETAIL.N_PRICE is
'单价';

/*==============================================================*/
/* Table: T_KILL_INFO                                           */
/*==============================================================*/
create table T_KILL_INFO 
(
   ID                   NUMBER               not null,
   I_PRODUCT_ID         NUMBER,
   I_USER_ID            NUMBER,
   VC_USRE_NAME         VARCHAR(20),
   DT_BID               DATE,
   N_TYPE               NUMBER,
   N_TOTAL_PRICE        NUMBER,
   N_ENABLE             NUMBER,
   constraint PK_T_KILL_INFO primary key (ID)
);

comment on table T_KILL_INFO is
'秒杀信息表';

comment on column T_KILL_INFO.ID is
'主键';

comment on column T_KILL_INFO.I_PRODUCT_ID is
'产品id';

comment on column T_KILL_INFO.I_USER_ID is
'出价人id';

comment on column T_KILL_INFO.VC_USRE_NAME is
'出价人姓名';

comment on column T_KILL_INFO.DT_BID is
'出价时间';

comment on column T_KILL_INFO.N_TYPE is
'出价类型';

comment on column T_KILL_INFO.N_TOTAL_PRICE is
'总价';

comment on column T_KILL_INFO.N_ENABLE is
'状态（0有效，1无效）';

/*==============================================================*/
/* Table: T_LOAN                                                */
/*==============================================================*/
create table T_LOAN 
(
   ID                   NUMBER               not null,
   N_ENABLE             NUMBER               default 0,
   I_APPLY_USER         NUMBER,
   VC_APPLY_USER_NAME   VARCHAR(20),
   VC_SUBNO             VARCHAR(32),
   VC_SUB_ALL_NAME      VARCHAR(100),
   DT_APPLY             DATE,
   VC_EXCUSE            VARCHAR(600),
   N_LOAN_TOTAL         NUMBER,
   DT_DEADLINE          DATE,
   VC_MORTGAGE          VARCHAR(200),
   VC_NOTE              VARCHAR(1000),
   N_FLAG               NUMBER               default 0,
   VC_FINANCENO         VARCHAR(32),
   VC_FINANCE_NAME      VARCHAR(100),
   VC_FINANCENO_REAL    VARCHAR(32),
   VC_FINANCE_NAME_REAL VARCHAR(100),
   DT_RESULT            DATE,
   VC_LOANNO			VARCHAR2(32),
   constraint PK_T_LOAN primary key (ID)
);

comment on table T_LOAN is
'在线申请贷款';

comment on column T_LOAN.ID is
'主键';

comment on column T_LOAN.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_LOAN.I_APPLY_USER is
'申请人id';

comment on column T_LOAN.VC_APPLY_USER_NAME is
'申请人姓名';

comment on column T_LOAN.VC_SUBNO is
'分供方编号';

comment on column T_LOAN.VC_SUB_ALL_NAME is
'分供方全名';

comment on column T_LOAN.DT_APPLY is
'申请时间';

comment on column T_LOAN.VC_EXCUSE is
'申请理由';

comment on column T_LOAN.N_LOAN_TOTAL is
'贷款总额';

comment on column T_LOAN.DT_DEADLINE is
'期限';

comment on column T_LOAN.VC_MORTGAGE is
'抵押';

comment on column T_LOAN.VC_NOTE is
'申请内容描述';

comment on column T_LOAN.N_FLAG is
'贷款状态（0，提交，1在洽谈，2，已贷款，3，贷不到款）';

comment on column T_LOAN.VC_FINANCENO is
'预向贷款公司编号';

comment on column T_LOAN.VC_FINANCE_NAME is
'预向贷款公司名称';

comment on column T_LOAN.vc_loanno
  is '申请贷款编号';

/*==============================================================*/
/* Table: T_LOAN_ASK                                            */
/*==============================================================*/
create table T_LOAN_ASK 
(
   ID                   NUMBER               not null,
   I_LOAN               NUMBER,
   DT_ASK               DATE,
   N_REPULSE            NUMBER,
   VC_CAUSE             VARCHAR(400),
   N_ACCRUAL            NUMBER,
   DT_ACCRUAL           DATE,
   VC_FINANCENO         VARCHAR(32),
   I_USER               NUMBER,
   VC_USER_NAME         VARCHAR(30),
   I_LOAN_HANDLE        NUMBER,
   N_ENABLE             NUMBER               default 0,
   N_ACCRUAL_LAST       NUMBER,
   constraint PK_T_LOAN_ASK primary key (ID)
);

comment on table T_LOAN_ASK is
'贷款询问';

comment on column T_LOAN_ASK.ID is
'主键';

comment on column T_LOAN_ASK.I_LOAN is
'申请贷款id';

comment on column T_LOAN_ASK.N_ACCRUAL is
'利息';

comment on column T_LOAN_ASK.DT_ACCRUAL is
'出利息时间';

comment on column T_LOAN_ASK.VC_FINANCENO is
'金融公司编号';

comment on column T_LOAN_ASK.I_USER is
'出利息人id';

comment on column T_LOAN_ASK.VC_USER_NAME is
'出利息人姓名';

comment on column T_LOAN_ASK.I_LOAN_HANDLE is
'处理信息表id';

comment on column T_LOAN_ASK.N_ENABLE is
'是否有效（0有效，1无效）';

/*==============================================================*/
/* Table: T_LOAN_HANDLE                                         */
/*==============================================================*/
create table T_LOAN_HANDLE 
(
   ID                   NUMBER               not null,
   I_LOAN               NUMBER,
   I_USER               NUMBER,
   VC_USER_NAME         VARCHAR(30),
   N_RESULT             NUMBER,
   N_TALK               NUMBER,
   DT_TALK              DATE,
   DT_CONFIRM           DATE,
   N_ENABLE             NUMBER               default 0,
   VC_FINANCENO         VARCHAR(32),
   constraint PK_T_LOAN_HANDLE primary key (ID)
);

comment on table T_LOAN_HANDLE is
'贷款处理信息表';

comment on column T_LOAN_HANDLE.ID is
'主键';

comment on column T_LOAN_HANDLE.I_LOAN is
'申请贷款表id';

comment on column T_LOAN_HANDLE.I_USER is
'处理员工id';

comment on column T_LOAN_HANDLE.VC_USER_NAME is
'处理员工姓名';

comment on column T_LOAN_HANDLE.N_RESULT is
'处理最终结果';

comment on column T_LOAN_HANDLE.N_TALK is
'是否在洽谈';

comment on column T_LOAN_HANDLE.DT_TALK is
'洽谈时间';

comment on column T_LOAN_HANDLE.DT_CONFIRM is
'确认时间';

comment on column T_LOAN_HANDLE.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_LOAN_HANDLE.VC_FINANCENO is
'最终给予贷款公司编号';

/*==============================================================*/
/* Table: T_MANGE_SCOPE                                         */
/*==============================================================*/
create table T_MANGE_SCOPE 
(
   ID                   NUMBER               not null,
   VC_NO                NUMBER,
   I_USER               NUMBER,
   N_ENABLE             NUMBER               default 0,
   constraint PK_T_MANGE_SCOPE primary key (ID)
);

comment on table T_MANGE_SCOPE is
'管理范围表';

comment on column T_MANGE_SCOPE.ID is
'主键';

comment on column T_MANGE_SCOPE.VC_NO is
'分供方编号（被管理着）';

comment on column T_MANGE_SCOPE.I_USER is
'员工id';

comment on column T_MANGE_SCOPE.N_ENABLE is
'是否有效（0有效，1无效）';

/*==============================================================*/
/* Table: T_MESSAGE                                             */
/*==============================================================*/
create table T_MESSAGE 
(
   ID                   NUMBER               not null,
   VC_TITLE             VARCHAR(50),
   VC_CONTENT           VARCHAR(400),
   VC_LINK_URL          VARCHAR(100),
   I_SEND_USER          NUMBER,
   VC_SEND_USER_NAME    VARCHAR(50),
   DT_SEND              DATE,
   I_ACCEPT_USER        NUMBER,
   VC_ACCEPT_USER_NAME  VARCHAR(50),
   DT_LOOK              DATE,
   N_IGNORE             NUMBER,
   N_ENABLE             NUMBER,
   N_TOP                NUMBER,
   constraint PK_T_MESSAGE primary key (ID)
);

comment on table T_MESSAGE is
'消息表';

comment on column T_MESSAGE.ID is
'主键';

comment on column T_MESSAGE.VC_TITLE is
'标题';

comment on column T_MESSAGE.VC_CONTENT is
'内容';

comment on column T_MESSAGE.VC_LINK_URL is
'链接地址';

comment on column T_MESSAGE.I_SEND_USER is
'发送人id';

comment on column T_MESSAGE.VC_SEND_USER_NAME is
'发送人姓名';

comment on column T_MESSAGE.DT_SEND is
'发送时间';

comment on column T_MESSAGE.I_ACCEPT_USER is
'收件人id';

comment on column T_MESSAGE.VC_ACCEPT_USER_NAME is
'收件人姓名';

comment on column T_MESSAGE.DT_LOOK is
'查阅时间';

comment on column T_MESSAGE.N_IGNORE is
'是否忽略（0是，1不是）';

comment on column T_MESSAGE.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_MESSAGE.N_TOP is
'是否置顶（0是，1不是）';

/*==============================================================*/
/* Table: T_ORDER                                               */
/*==============================================================*/
create table T_ORDER 
(
   ID                   NUMBER               not null,
   VC_LOAD_ADDRESS      VARCHAR(150),
   VC_LOAD_CONTACT      VARCHAR(20),
   VC_LOAD_TEL          VARCHAR(20),
   DT_SHIP              DATE,
   DT_ARRIVE            DATE,
   I_START_ID 			NUMBER,
   I_END_ID				NUMBER,
   VC_START_CITY        VARCHAR(30),
   VC_DEST_CITY         VARCHAR(30),
   VC_RECEIVE_ADDRESS   VARCHAR(150),
   VC_RECEIVE_CONTACT   VARCHAR(20),
   VC_RECEIVE_TEL       VARCHAR(20),
   I_CAR_STYLE          NUMBER,
   N_TOTAL_CAR          NUMBER               default 0,
   N_ShipedQty			NUMBER               default 0,
   N_ENABLE             NUMBER               default 0,
   I_USER               NUMBER,
   VC_SUBNO             VARCHAR(32),
   VC_ORDERNO			VARCHAR(32),
   constraint PK_T_ORDER primary key (ID)
);

comment on table T_ORDER is
'订单表';

comment on column T_ORDER.ID is
'主键';

comment on column T_ORDER.VC_LOAD_ADDRESS is
'装货地址';

comment on column T_ORDER.VC_LOAD_CONTACT is
'联系人';

comment on column T_ORDER.VC_LOAD_TEL is
'联系电话';

comment on column T_ORDER.DT_SHIP is
'要求发运日';

comment on column T_ORDER.DT_ARRIVE is
'要求到货日';

comment on column T_ORDER.VC_START_CITY is
'起运城市';

comment on column T_ORDER.VC_DEST_CITY is
'目的城市';

comment on column T_ORDER.VC_RECEIVE_ADDRESS is
'收货地址';

comment on column T_ORDER.VC_RECEIVE_CONTACT is
'收货联系人';

comment on column T_ORDER.VC_RECEIVE_TEL is
'收货人电话';

comment on column T_ORDER.I_CAR_STYLE is
'车型号';

comment on column T_ORDER.N_TOTAL_CAR is
'数量';

comment on column T_ORDER.N_ShipedQty is
'发运数量';

comment on column T_ORDER.N_ENABLE is
'状态（0为有效，1为无效）';

comment on column T_ORDER.I_USER is
'录入用户id';

comment on column T_ORDER.VC_SUBNO is
'所属分供方编号';

/*==============================================================*/
/* Table: T_PRODUCT                                             */
/*==============================================================*/
create table T_PRODUCT 
(
   ID                   NUMBER               not null,
   VC_START             VARCHAR(100),
   VC_END               VARCHAR(100),
   DT_ARRIVE_TIME       DATE,
   N_KILOMETRE          NUMBER,
   N_MIN_PRICE          NUMBER,
   DT_KILL_START        DATE,
   DT_KILL_END          DATE,
   VC_REQUIRE           VARCHAR(500),
   N_ENABLE             NUMBER               default 0,
   I_USER_ID            NUMBER,
   VC_USER_NAME         VARCHAR(30),
   vc_subno       VARCHAR2(32) not null,
   dt_release     DATE default sysdate,
   VC_KILLNO		VARCHAR2(32),
   N_BID			NUMBER               default 1,
   constraint PK_T_PRODUCT primary key (ID)
);

comment on table T_PRODUCT is
'抢单产品表';

comment on column T_PRODUCT.ID is
'主键';

comment on column T_PRODUCT.VC_START is
'路线起始点';

comment on column T_PRODUCT.VC_END is
'路线终点';

comment on column T_PRODUCT.DT_ARRIVE_TIME is
'要求抵达时间';

comment on column T_PRODUCT.N_KILOMETRE is
'公里数';

comment on column T_PRODUCT.N_MIN_PRICE is
'标准价格（秒杀起步价）';

comment on column T_PRODUCT.DT_KILL_START is
'秒杀开始时间';

comment on column T_PRODUCT.DT_KILL_END is
'秒杀结束时间';

comment on column T_PRODUCT.VC_REQUIRE is
'要求';

comment on column T_PRODUCT.N_ENABLE is
'状态（0为有效，1为无效）';

comment on column T_PRODUCT.I_USER_ID is
'发布者id';

comment on column T_PRODUCT.VC_USER_NAME is
'发布者姓名';
comment on column T_PRODUCT.vc_subno
  is '所属分供方编号';
  
comment on column T_PRODUCT.dt_release
  is '发布时间';
  
comment on column T_PRODUCT.vc_killno
  is '秒杀产品编号';
  
  comment on column T_PRODUCT.n_bid
  is '是否中标（0为中标，1为没中标）';

/*==============================================================*/
/* Table: T_RESOURCE                                            */
/*==============================================================*/
create table T_RESOURCE 
(
   ID                   NUMBER               not null,
   VC_RESOURCE_NAME     VARCHAR(40),
   N_TYPE               NUMBER,
   VC_URL               VARCHAR(100),
   VC_ICON				VARCHAR(100),
   N_ENABLE             NUMBER               default 0,
   N_ROOT               NUMBER               default 1,
   N_LEAF               NUMBER               default 1,
   VC_DESC              VARCHAR(100),
   I_PARENT             NUMBER,
   N_SORT               NUMBER,
   constraint PK_T_RESOURCE primary key (ID)
);

comment on table T_RESOURCE is
'资源表';

comment on column T_RESOURCE.ID is
'主键';

comment on column T_RESOURCE.VC_RESOURCE_NAME is
'资源名称';

comment on column T_RESOURCE.N_TYPE is
'资源类型（菜单0，功能1，操作2）';

comment on column T_RESOURCE.VC_URL is
'资源地址';

comment on column T_RESOURCE.VC_ICON is
'资源显示的图片路径';

comment on column T_RESOURCE.N_ENABLE is
'资源状态（0有效，1无效）';

comment on column T_RESOURCE.N_ROOT is
'是否根节点（0是，1不是）';

comment on column T_RESOURCE.N_LEAF is
'是否叶子节点（0是，1不是）';

comment on column T_RESOURCE.VC_DESC is
'资源描述';

comment on column T_RESOURCE.I_PARENT is
'父节点id';

comment on column T_RESOURCE.N_SORT is
'排序';

/*==============================================================*/
/* Table: T_RESOURCE_ARCHIVE                                    */
/*==============================================================*/
create table T_RESOURCE_ARCHIVE 
(
   ID                   NUMBER               not null,
   I_ARCHIVE_TYPE       NUMBER,
   N_ENABLE             NUMBER               default 0,
   I_RESOURCE           NUMBER,
   constraint PK_T_RESOURCE_ARCHIVE primary key (ID)
);

comment on table T_RESOURCE_ARCHIVE is
'资源所属表';

comment on column T_RESOURCE_ARCHIVE.ID is
'主键';

comment on column T_RESOURCE_ARCHIVE.I_ARCHIVE_TYPE is
'档案类型id';

comment on column T_RESOURCE_ARCHIVE.N_ENABLE is
'是否有效（0有效，1无效）';

/*==============================================================*/
/* Table: T_ROLE                                                */
/*==============================================================*/
create table T_ROLE 
(
   ID                   NUMBER               not null,
   VC_ROLE_NAME         VARCHAR(20),
   N_ENABLE             NUMBER               default 0,
   VC_DESC              VARCHAR(50),
   constraint PK_T_ROLE primary key (ID)
);

comment on table T_ROLE is
'角色表';

comment on column T_ROLE.ID is
'主键';

comment on column T_ROLE.VC_ROLE_NAME is
'角色名称';

comment on column T_ROLE.N_ENABLE is
'角色状态(0为有效，1为无效)';

comment on column T_ROLE.VC_DESC is
'角色描述';

/*==============================================================*/
/* Table: T_ROLE_RESOURCE                                       */
/*==============================================================*/
create table T_ROLE_RESOURCE 
(
   ID                   NUMBER               not null,
   I_ROLE_ID            NUMBER,
   I_RESOURCE_ID        NUMBER,
   constraint PK_T_ROLE_RESOURCE primary key (ID)
);

comment on table T_ROLE_RESOURCE is
'角色资源关联表';

comment on column T_ROLE_RESOURCE.ID is
'主键';

comment on column T_ROLE_RESOURCE.I_ROLE_ID is
'角色id';

comment on column T_ROLE_RESOURCE.I_RESOURCE_ID is
'资源id';

/*==============================================================*/
/* Table: T_SHIPHEAD                                            */
/*==============================================================*/
create table T_SHIPHEAD 
(
   ID                   NUMBER               not null,
   VC_SHIPNO            VARCHAR(30),
   DT_CREATE            DATE,
   N_SHIP_TYPE          NUMBER               default 0,
   I_TRUCK_ID           NUMBER,
   N_ENABLE             NUMBER               default 0,
   VC_SUBNO             VARCHAR(32),
   N_ARORDER			NUMBER,				 default 1,
   I_ARORDER_USER		NUMBER,		
   DT_ARORDER			DATE,			
   constraint PK_T_SHIPHEAD primary key (ID)
);

comment on table T_SHIPHEAD is
'发运主表';

comment on column T_SHIPHEAD.ID is
'主键';

comment on column T_SHIPHEAD.VC_SHIPNO is
'发运指令号';

comment on column T_SHIPHEAD.DT_CREATE is
'生成指令时间';

comment on column T_SHIPHEAD.N_SHIP_TYPE is
'发运指令类型(0:重载、1空载)';

comment on column T_SHIPHEAD.I_TRUCK_ID is
'运输车ID';

comment on column T_SHIPHEAD.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_SHIPHEAD.VC_SUBNO is
'所属分供方编号';

comment on column T_SHIPHEAD.N_ARORDER is
'指令是否结算应收  0已结算应收  1 未';

comment on column T_SHIPHEAD.I_ARORDER_USER is
'指令结算应收  操作人 用户ID';

comment on column T_SHIPHEAD.DT_ARORDER is
'指令结算应收  操作时间';


/*==============================================================*/
/* Table: T_SHIPLINE                                            */
/*==============================================================*/
create table T_SHIPLINE 
(
   ID                   NUMBER               not null,
   N_ENABLE             NUMBER               default 0,
   I_SHIPHEAD           NUMBER,
   I_ORDER_ID           NUMBER,
   VC_START_CITY        VARCHAR(50),
   VC_DEST_CITY         VARCHAR(50),
   DT_ADD               DATE,
   N_SHIP_QTY           NUMBER,
   N_QTY                NUMBER,
   N_APKILOMETER        NUMBER,
   N_ENTRANCE           NUMBER               default 1,
   I_ENTRANCE_USER      NUMBER,
   DT_ENTRANCE          DATE,
   N_LOAD               NUMBER               default 1,
   I_LOAD_USER          NUMBER,
   DT_LOAD              DATE,
   N_SHIP               NUMBER               default 1,
   I_SHIP_USER          NUMBER,
   DT_SHIP              DATE,
   N_RETURN             NUMBER               default 1,
   DT_RETURN            DATE,
   I_RETURN_USER        NUMBER,
   N_CURRENT_STATUS		NUMBER,				 default 0,
   constraint PK_T_SHIPLINE primary key (ID)
);

comment on table T_SHIPLINE is
'发运明细表';

comment on column T_SHIPLINE.ID is
'主键';

comment on column T_SHIPLINE.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_SHIPLINE.I_SHIPHEAD is
'发运主表id';

comment on column T_SHIPLINE.I_ORDER_ID is
'订单id';

comment on column T_SHIPLINE.VC_START_CITY is
'开始城市';

comment on column T_SHIPLINE.VC_DEST_CITY is
'目的城市';

comment on column T_SHIPLINE.DT_ADD is
'添加时间';

comment on column T_SHIPLINE.N_SHIP_QTY is
'装运数量';

comment on column T_SHIPLINE.N_QTY is
'应收结算数量';

comment on column T_SHIPLINE.N_APKILOMETER is
'应收公里';

comment on column T_SHIPLINE.N_ENTRANCE is
'是否入场(0是，1不是)';

comment on column T_SHIPLINE.I_ENTRANCE_USER is
'入场确认人id';

comment on column T_SHIPLINE.DT_ENTRANCE is
'入场时间';

comment on column T_SHIPLINE.N_LOAD is
'是否装车(0是，1不是)';

comment on column T_SHIPLINE.I_LOAD_USER is
'装车确认人id';

comment on column T_SHIPLINE.DT_LOAD is
'装车时间';

comment on column T_SHIPLINE.N_SHIP is
'是否发运(0是，1不是)';

comment on column T_SHIPLINE.I_SHIP_USER is
'发运确认人id';

comment on column T_SHIPLINE.DT_SHIP is
'发运时间';

comment on column T_SHIPLINE.N_RETURN is
'是否回单(0是，1不是)';

comment on column T_SHIPLINE.DT_RETURN is
'回单时间';

comment on column T_SHIPLINE.I_RETURN_USER is
'回单确认人id';

comment on column T_SHIPLINE.N_CURRENT_STATUS is
'当前状态';

/*==============================================================*/
/* Table: T_SUBSUPPLIERS                                        */
/*==============================================================*/
create table T_SUBSUPPLIERS 
(
   ID                   number               not null,
   VC_ALL_NAME          VARCHAR(100),
   VC_SMIPLE_NAME       VARCHAR(50),
   VC_ADDRESS           VARCHAR(150),
   VC_SCALE             VARCHAR(50),
   N_ENABLE             NUMBER               default 0,
   VC_PROVINCE          VARCHAR(50),
   VC_CITY              VARCHAR(60),
   VC_AREA              VARCHAR(150),
   VC_DETAILED_ADDRESS  VARCHAR(150),
   VC_REGISTER_ADDRESS  VARCHAR(150),
   N_ENABLE_KILL        NUMBER               default 1,
   N_ENABLE_ORDER       NUMBER               default 1,
   VC_ORDER_FILE        VARCHAR(50),
   VC_KILL_FILE         VARCHAR(50),
   N_ENABLE_APPLY       NUMBER               default 1,
   VC_SUBNO             VARCHAR(32)          not null,
   constraint PK_T_SUBSUPPLIERS primary key (ID)
);

comment on table T_SUBSUPPLIERS is
'分供方档案表';

comment on column T_SUBSUPPLIERS.ID is
'主键';

comment on column T_SUBSUPPLIERS.VC_ALL_NAME is
'分供方全称';

comment on column T_SUBSUPPLIERS.VC_SMIPLE_NAME is
'分供方简称';

comment on column T_SUBSUPPLIERS.VC_ADDRESS is
'分供方地址';

comment on column T_SUBSUPPLIERS.VC_SCALE is
'分供方规模（有多少辆车）';

comment on column T_SUBSUPPLIERS.N_ENABLE is
'是否有效(0有效，1无效)';

comment on column T_SUBSUPPLIERS.VC_PROVINCE is
'所属省份';

comment on column T_SUBSUPPLIERS.VC_CITY is
'所属城市';

comment on column T_SUBSUPPLIERS.VC_AREA is
'所属地区';

comment on column T_SUBSUPPLIERS.VC_DETAILED_ADDRESS is
'详细地址';

comment on column T_SUBSUPPLIERS.VC_REGISTER_ADDRESS is
'公司注册地址';

comment on column T_SUBSUPPLIERS.N_ENABLE_KILL is
'是否可抢单(0为可，1为不可，默认为1)';

comment on column T_SUBSUPPLIERS.N_ENABLE_ORDER is
'是否可下单(0为可，1为不可，默认为1)';

comment on column T_SUBSUPPLIERS.VC_ORDER_FILE is
'下单资质证明';

comment on column T_SUBSUPPLIERS.VC_KILL_FILE is
'抢单资质证明';

comment on column T_SUBSUPPLIERS.N_ENABLE_APPLY is
'是否可贷款(0为可，1为不可，默认为1)';

comment on column T_SUBSUPPLIERS.VC_SUBNO is
'分供方编号';

/*==============================================================*/
/* Table: T_SUB_CAR_STYLE                                       */
/*==============================================================*/
create table T_SUB_CAR_STYLE 
(
   ID                   NUMBER               not null,
   VC_CAR_STYLE         VARCHAR(50),
   N_ENABLE             NUMBER               default 0,
   VC_SUBNO             VARCHAR(32),
   constraint PK_T_SUB_CAR_STYLE primary key (ID)
);

comment on table T_SUB_CAR_STYLE is
'分供方车型管理';

comment on column T_SUB_CAR_STYLE.ID is
'主键';

comment on column T_SUB_CAR_STYLE.VC_CAR_STYLE is
'车型名称';

comment on column T_SUB_CAR_STYLE.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_SUB_CAR_STYLE.VC_SUBNO is
'所属分供方';

/*==============================================================*/
/* Table: T_TRUCK_DRIVER                                        */
/*==============================================================*/
create table T_TRUCK_DRIVER 
(
   ID                   NUMBER               not null,
   VC_CAR_NO            VARCHAR(20),
   VC_DRIVER_NAME       VARCHAR(20),
   VC_DRIVER_TEL        VARCHAR(20),
   N_ENABLE             NUMBER               default 0,
   VC_FLEETNO           VARCHAR(32),
   VC_SUBNO             VARCHAR(32),
   N_STATUS		 		NUMBER               default 1, 
   constraint PK_T_TRUCK_DRIVER primary key (ID)
);

comment on table T_TRUCK_DRIVER is
'车辆司机表';

comment on column T_TRUCK_DRIVER.ID is
'主键';

comment on column T_TRUCK_DRIVER.VC_CAR_NO is
'车牌号';

comment on column T_TRUCK_DRIVER.VC_DRIVER_NAME is
'司机名称';

comment on column T_TRUCK_DRIVER.VC_DRIVER_TEL is
'司机手机号';

comment on column T_TRUCK_DRIVER.N_ENABLE is
'是否有效（0有效，1无效）';

comment on column T_TRUCK_DRIVER.N_STATUS is
'车辆是否可用（0有效，1无效）  调度指令回单确认释放车辆';


comment on column T_TRUCK_DRIVER.VC_FLEETNO is
'车队编号';

comment on column T_TRUCK_DRIVER.VC_SUBNO is
'所属分供方编号';

/*==============================================================*/
/* Table: T_USER                                                */
/*==============================================================*/
create table T_USER 
(
   ID                   NUMBER               not null,
   VC_USERNAME          VARCHAR(20),
   VC_ACCOUNT           VARCHAR(32)          not null,
   VC_PASSWORD          VARCHAR(32),
   N_ENABLE             NUMBER               default 0,
   DT_ADDTIME           date                 default SYSDATE,
   I_ARCHIVE_TYPE       NUMBER,
   I_ARCHIVE            NUMBER,
   constraint PK_T_USER primary key (ID)
);

comment on table T_USER is
'用户登陆表';

comment on column T_USER.ID is
'主键';

comment on column T_USER.VC_USERNAME is
'用户名';

comment on column T_USER.VC_ACCOUNT is
'账号';

comment on column T_USER.VC_PASSWORD is
'密码';

comment on column T_USER.N_ENABLE is
'是否有效（0有效，1无效)';

comment on column T_USER.DT_ADDTIME is
'添加时间';

comment on column T_USER.I_ARCHIVE_TYPE is
'档案类型id';

comment on column T_USER.I_ARCHIVE is
'档案id';

-- Create table
create table T_PRODUCT_CAR_STYLE
(
  id           NUMBER not null,
  vc_car_style VARCHAR2(20),
  n_car_count  NUMBER,
  i_product_id NUMBER,
  n_enable     NUMBER default 0
)

-- Add comments to the table 
comment on table T_PRODUCT_CAR_STYLE
  is '抢单车型信息';
-- Add comments to the columns 
comment on column T_PRODUCT_CAR_STYLE.id
  is '主键';
comment on column T_PRODUCT_CAR_STYLE.vc_car_style
  is '车型';
comment on column T_PRODUCT_CAR_STYLE.n_car_count
  is '数量';
comment on column T_PRODUCT_CAR_STYLE.i_product_id
  is '产品id';
comment on column T_PRODUCT_CAR_STYLE.n_enable
  is '状态（0为有效，1为无效）';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_PRODUCT_CAR_STYLE
  add constraint PK_T_PRODUCT_CAR_STYLE primary key (ID)

/*==============================================================*/
/* Table: T_USER_ROLE                                           */
/*==============================================================*/
create table T_USER_ROLE 
(
   ID                   NUMBER               not null,
   I_USER               NUMBER,
   I_ROLE               NUMBER,
   I_ENABLE             NUMBER               default 0,
   constraint PK_T_USER_ROLE primary key (ID)
);

comment on table T_USER_ROLE is
'用户角色关联表';

comment on column T_USER_ROLE.ID is
'主键';

comment on column T_USER_ROLE.I_USER is
'用户id';

comment on column T_USER_ROLE.I_ROLE is
'角色id';

comment on column T_USER_ROLE.I_ENABLE is
'是否有效（0有效，1无效）';

alter table T_BID_WIN
   add constraint FK_T_BID_WI_REFERENCE_T_KILL_I foreign key (I_KILL_INFO)
      references T_KILL_INFO (ID);

alter table T_BID_WIN
   add constraint FK_T_BID_WI_REFERENCE_T_PRODUC foreign key (I_PRODUCT)
      references T_PRODUCT (ID);

alter table T_CAR_STYLE
   add constraint FK_T_CAR_ST_REFERENCE_T_PRODUC foreign key (I_PRODUCT)
      references T_PRODUCT (ID);

alter table T_CLT
   add constraint FK_T_CLT_REFERENCE_T_DEPT foreign key (I_DEPT)
      references T_DEPT (ID);

alter table T_DRIVER_COST
   add constraint FK_T_DRIVER_REFERENCE_T_TRUCK_ foreign key (I_TRUCK_ID)
      references T_TRUCK_DRIVER (ID);

alter table T_FINANCE
   add constraint FK_T_FINANC_REFERENCE_T_COMPAN foreign key (ID)
      references T_COMPANY_TYPE (ID);

alter table T_KILL_DETAIL
   add constraint FK_T_KILL_D_REFERENCE_T_KILL_I foreign key (I_KILL_INFO)
      references T_KILL_INFO (ID);

alter table T_KILL_DETAIL
   add constraint FK_T_KILL_D_REFERENCE_T_CAR_ST foreign key (I_CAR_STYLE)
      references T_CAR_STYLE (ID);

alter table T_KILL_INFO
   add constraint FK_T_KILL_I_REFERENCE_T_PRODUC foreign key (I_PRODUCT_ID)
      references T_PRODUCT (ID);

alter table T_LOAN_ASK
   add constraint FK_T_LOAN_A_REFERENCE_T_LOAN foreign key (I_LOAN)
      references T_LOAN (ID);

alter table T_LOAN_ASK
   add constraint FK_T_LOAN_A_REFERENCE_T_LOAN_H foreign key (I_LOAN_HANDLE)
      references T_LOAN_HANDLE (ID);

alter table T_LOAN_HANDLE
   add constraint FK_T_LOAN_H_REFERENCE_T_LOAN foreign key (I_LOAN)
      references T_LOAN (ID);

alter table T_ORDER
   add constraint FK_T_ORDER_REFERENCE_T_SUB_CA foreign key (I_CAR_STYLE)
      references T_SUB_CAR_STYLE (ID);

alter table T_ORDER
   add constraint FK_T_ORDER_REFERENCE_T_USER foreign key (I_USER)
      references T_USER (ID);

alter table T_PRODUCT
   add constraint FK_T_PRODUC_REFERENCE_T_USER foreign key (I_USER_ID)
      references T_USER (ID);

alter table T_RESOURCE_ARCHIVE
   add constraint FK_T_RESOUR_REFERENCE_T_ARCHIV foreign key (I_ARCHIVE_TYPE)
      references T_ARCHIVE_TYPE (ID);

alter table T_RESOURCE_ARCHIVE
   add constraint FK_T_RESOUR_REFERENCE_T_RESOUR foreign key (I_RESOURCE)
      references T_RESOURCE (ID);

alter table T_ROLE_RESOURCE
   add constraint FK_T_ROLE_R_REFERENCE_T_ROLE foreign key (I_ROLE_ID)
      references T_ROLE (ID);

alter table T_ROLE_RESOURCE
   add constraint FK_T_ROLE_R_REFERENCE_T_RESOUR foreign key (I_RESOURCE_ID)
      references T_RESOURCE (ID);

alter table T_SHIPHEAD
   add constraint FK_T_SHIPHE_REFERENCE_T_TRUCK_ foreign key (I_TRUCK_ID)
      references T_TRUCK_DRIVER (ID);

alter table T_SHIPLINE
   add constraint FK_T_SHIPLI_REFERENCE_T_ORDER foreign key (I_ORDER_ID)
      references T_ORDER (ID);

alter table T_SHIPLINE
   add constraint FK_T_SHIPLI_REFERENCE_T_SHIPHE foreign key (I_SHIPHEAD)
      references T_SHIPHEAD (ID);

alter table T_USER
   add constraint FK_T_USER_REFERENCE_T_FINANC foreign key (I_ARCHIVE)
      references T_FINANCE (ID);

alter table T_USER
   add constraint FK_T_USER_REFERENCE_T_SUBSUP foreign key (I_ARCHIVE)
      references T_SUBSUPPLIERS (ID);

alter table T_USER
   add constraint FK_T_USER_REFERENCE_T_CLT foreign key (I_ARCHIVE)
      references T_CLT (ID);

alter table T_USER
   add constraint FK_T_USER_REFERENCE_T_ARCHIV foreign key (I_ARCHIVE_TYPE)
      references T_ARCHIVE_TYPE (ID);

alter table T_USER_ROLE
   add constraint FK_T_USER_R_REFERENCE_T_USER foreign key (I_USER)
      references T_USER (ID);

alter table T_USER_ROLE
   add constraint FK_T_USER_R_REFERENCE_T_ROLE foreign key (I_ROLE)
      references T_ROLE (ID);


---建立序列
Create Sequence S_ARCHIVE_TYPE;
Create Sequence S_ARKILOMETER;
Create Sequence S_ARORDER;
Create Sequence S_BID_WIN;
Create Sequence S_DRIVER_COST;
Create Sequence S_FINANCE;
Create Sequence S_KILL_INFO;
Create Sequence S_LOAN;
Create Sequence S_MESSAGE;
Create Sequence S_ORDER;
Create Sequence S_PRODUCT;
Create Sequence S_PRODUCT_CAR_STYLE;

Create Sequence S_RESOURCE;
Create Sequence S_ROLE;
Create Sequence S_ROLE_RESOURCE;
Create Sequence S_SHIPHEAD;
Create Sequence S_SHIPLINE;


Create Sequence S_SUBSUPPLIERS;
Create Sequence S_SUB_CAR_STYLE;
Create Sequence S_TRUCK_DRIVER;
Create Sequence S_USER;
Create Sequence S_USER_ROLE;

Create Sequence S_RESOURCE_ARCHIVE;
Create Sequence S_DEPT;
Create Sequence S_CLT;

Create Sequence S_MANGE_SCOPE;

--档案类型表增加  所属表名
alter table T_ARCHIVE_TYPE add 

Create Sequence S_CLT;

--创建省份表
CREATE TABLE T_Province(
   ID   NUMBER NOT NULL,
   ProvinceName   VARCHAR2(50)   NULL,
   DateCreated   DATE  NULL,
   DateUpdated   DATE  NULL,
  constraint PK_S_Province PRIMARY KEY (ID) 
  )
  --创建城市表
 CREATE TABLE  T_City (
   ID   number   NOT NULL,
	 CityName   VARCHAR2(50)   NULL,
	 ZipCode   VARCHAR2(50)   NULL,
	 ProvinceID   number  NULL,
	 DateCreated   date  NULL,
	 DateUpdated   date  NULL,
 CONSTRAINT  PK_S_City  PRIMARY KEY (ID)

)
Create Sequence S_PIC_NEW;
-- Create table图片新闻
create table T_PIC_NEW
(
  id          NUMBER not null,
  n_enable    NUMBER default 0,
  vc_desc     VARCHAR2(200),
  vc_pic_url  VARCHAR2(200),
  vc_link_url VARCHAR2(200),
  n_disaplay  NUMBER default 0,
  i_user      NUMBER,
  dt_add      DATE default SYSDATE,
  n_type      NUMBER default 0
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table T_PIC_NEW
  is '图片新闻';
-- Add comments to the columns 
comment on column T_PIC_NEW.id
  is '主键';
comment on column T_PIC_NEW.n_enable
  is '是否可用';
comment on column T_PIC_NEW.vc_desc
  is '描述';
comment on column T_PIC_NEW.vc_pic_url
  is '图片地址';
comment on column T_PIC_NEW.vc_link_url
  is '连接地址';
comment on column T_PIC_NEW.n_disaplay
  is '是否展示';
comment on column T_PIC_NEW.i_user
  is '添加用户';
comment on column T_PIC_NEW.dt_add
  is '添加时间';
comment on column T_PIC_NEW.n_type
  is '新闻类型（0为文字新闻，1为图片新闻）';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_PIC_NEW
  add constraint PK_PIC_NEW_ID primary key (ID);
