package liquibase.database.statement.generator;

import liquibase.database.statement.AddDefaultValueStatement;
import liquibase.database.statement.syntax.Sql;
import liquibase.database.statement.syntax.UnparsedSql;
import liquibase.database.Database;
import liquibase.database.SybaseASADatabase;
import liquibase.database.structure.Column;
import liquibase.database.structure.Table;

public class AddDefaultValueGeneratorSybaseASA extends AddDefaultValueGenerator {
    public int getSpecializationLevel() {
        return SPECIALIZATION_LEVEL_DATABASE_SPECIFIC;
    }

    public boolean isValidGenerator(AddDefaultValueStatement statement, Database database) {
        return database instanceof SybaseASADatabase;
    }

    public Sql[] generateSql(AddDefaultValueStatement statement, Database database) {
        return new Sql[]{
                new UnparsedSql("ALTER TABLE " + database.escapeTableName(statement.getSchemaName(), statement.getTableName()) + " MODIFY " + database.escapeColumnName(statement.getSchemaName(), statement.getTableName(), statement.getColumnName()) + " DEFAULT " + database.convertJavaObjectToString(statement.getDefaultValue()),
                        new Column()
                                .setTable(new Table(statement.getTableName()).setSchema(statement.getSchemaName()))
                                .setName(statement.getColumnName()))
        };
    }
}