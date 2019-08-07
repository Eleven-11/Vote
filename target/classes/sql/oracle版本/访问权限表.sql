DROP TABLE SYS_ACCESS_PERMISSION_TEST CASCADE CONSTRAINTS;

CREATE TABLE SYS_ACCESS_PERMISSION_TEST
(
  ID           VARCHAR2(32)                    NOT NULL,
  URL          VARCHAR2(50),
  ROLES        VARCHAR2(255),
  SORT         NUMBER,
  IS_DELETED   VARCHAR2(1)                     DEFAULT 0,
  CREATE_USER  VARCHAR2(50),
  CREATE_TIME  TIMESTAMP(6)                    DEFAULT SYSDATE,
  UPDATE_USER  VARCHAR2(50),
  UPDATE_TIME  TIMESTAMP(6)                    DEFAULT SYSDATE
)

ALTER TABLE SYS_ACCESS_PERMISSION_TEST ADD CONSTRAINT PK_PERMISSION PRIMARY KEY (ID)

create unique index uidx_request_url on sys_access_permission_test (url)

COMMENT ON TABLE SYS_ACCESS_PERMISSION_TEST IS '访问权限表';

COMMENT ON COLUMN SYS_ACCESS_PERMISSION_TEST.URL IS '访问链接';

COMMENT ON COLUMN SYS_ACCESS_PERMISSION_TEST.ROLES IS '角色列表用,分割';

COMMENT ON COLUMN SYS_ACCESS_PERMISSION_TEST.SORT IS '排序号';

COMMENT ON COLUMN SYS_ACCESS_PERMISSION_TEST.IS_DELETED IS '是否删除';

COMMENT ON COLUMN SYS_ACCESS_PERMISSION_TEST.CREATE_USER IS '创建用户';

COMMENT ON COLUMN SYS_ACCESS_PERMISSION_TEST.CREATE_TIME IS '创建时间';

COMMENT ON COLUMN SYS_ACCESS_PERMISSION_TEST.UPDATE_USER IS '更新用户';

COMMENT ON COLUMN SYS_ACCESS_PERMISSION_TEST.UPDATE_TIME IS '更新时间';
