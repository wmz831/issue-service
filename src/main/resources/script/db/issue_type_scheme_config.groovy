package script.db


databaseChangeLog(logicalFilePath: 'issue_type_scheme_config.groovy') {
    changeSet(id: '2018-08-10-create-issue-type-scheme-config', author: 'shinan.chen@hand-china.com') {
        createTable(tableName: 'issue_type_scheme_config') {
            column(name: 'id', type: 'BIGINT UNSIGNED', autoIncrement: 'true', remarks: 'ID,主键') {
                constraints(primaryKey: 'true')
            }
            column(name: 'scheme_id', type: 'BIGINT UNSIGNED', remarks: '问题方案id') {
                constraints(nullable: 'false')
            }
            column(name: 'issue_type_id', type: 'BIGINT UNSIGNED', remarks: '问题类型id') {
                constraints(nullable: 'false')
            }
            column(name: 'sequence', type: 'DECIMAL', defaultValue: "0", remarks: '排序') {
                constraints(nullable: 'false')
            }
            column(name: 'organization_id', type: 'BIGINT UNSIGNED', remarks: '组织id') {
                constraints(nullable: 'false')
            }

            column(name: "object_version_number", type: "BIGINT UNSIGNED", defaultValue: "1")
            column(name: "created_by", type: "BIGINT UNSIGNED", defaultValue: "0")
            column(name: "creation_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_updated_by", type: "BIGINT UNSIGNED", defaultValue: "0")
            column(name: "last_update_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
        createIndex(tableName: "issue_type_scheme_config", indexName: "issue_type_scheme_config_n1") {
            column(name: "scheme_id", type: "VARCHAR(64)")
        }
        createIndex(tableName: "issue_type_scheme_config", indexName: "issue_type_scheme_config_n2") {
            column(name: "issue_type_id", type: "VARCHAR(255)")
        }
        createIndex(tableName: "issue_type_scheme_config", indexName: "issue_type_scheme_config_n3") {
            column(name: "organization_id", type: "BIGINT UNSIGNED")
        }
    }

    changeSet(id: '2018-12-07-fix-add-default-issue-auto-test-config', author: 'shinan.chenX@gmail') {
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "insert into issue_type_scheme_config(scheme_id,issue_type_id,organization_id,sequence) " +
            "select its.id as scheme_id,it.id as issue_type_id,it.organization_id,'2' as sequence " +
            "from issue_type_scheme its " +
            "LEFT JOIN issue_type it on its.organization_id = it.organization_id " +
            "where its.apply_type = 'test' " +
            "and it.type_code = 'issue_auto_test'"
        }
    }
}