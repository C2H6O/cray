package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class LeaseDaoGenerator {
  public static void main(String[] args) {
    Schema schema = new Schema(1, "net.doubov.myredditclient.model");
    Entity person = schema.addEntity("Person");
    person.addIdProperty();
    person.addStringProperty("name");
    person.addStringProperty("comment");

    Entity lease = schema.addEntity("Lease");
    lease.addIdProperty();
    lease.addStringProperty("item");
    lease.addStringProperty("comment");
    lease.addLongProperty("leaseDate");
    lease.addLongProperty("returnDate");
    lease.addStringProperty("condition");

    Property personId = lease.addLongProperty("personId").getProperty();
    lease.addToOne(person, personId);

    ToMany personToLease = person.addToMany(lease, personId);
    personToLease.setName("leases");

    Entity resultSet = schema.addEntity("ResultSet");
    resultSet.addIdProperty();
    resultSet.addStringProperty("name");
    resultSet.addStringProperty("uid");

    Property resultSetUid = resultSet.addLongProperty("resultSetId").getProperty();
    resultSet.addToMany(resultSet, resultSetUid).setName("children");

    Entity result = schema.addEntity("Result");
    result.addIdProperty();
    result.addStringProperty("value");

    Property resultResultSetUid = result.addLongProperty("resultSetId").getProperty();
    result.addToOne(resultSet, resultResultSetUid).setName("resultSet");

    resultSet.addToMany(result, resultResultSetUid).setName("results");



    try {
      new DaoGenerator().generateAll(schema, "../app/src/main/java");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
