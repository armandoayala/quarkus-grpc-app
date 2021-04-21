// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: project/person.proto

package org.lab.arm.quarkus.grpc.model;

public interface PersonEntityOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PersonEntity)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * Unique ID number for this person.
   * </pre>
   *
   * <code>int64 id = 2;</code>
   * @return The id.
   */
  long getId();

  /**
   * <code>string email = 3;</code>
   * @return The email.
   */
  java.lang.String getEmail();
  /**
   * <code>string email = 3;</code>
   * @return The bytes for email.
   */
  com.google.protobuf.ByteString
      getEmailBytes();

  /**
   * <code>repeated .PhoneNumber phones = 4;</code>
   */
  java.util.List<org.lab.arm.quarkus.grpc.model.PhoneNumber> 
      getPhonesList();
  /**
   * <code>repeated .PhoneNumber phones = 4;</code>
   */
  org.lab.arm.quarkus.grpc.model.PhoneNumber getPhones(int index);
  /**
   * <code>repeated .PhoneNumber phones = 4;</code>
   */
  int getPhonesCount();
  /**
   * <code>repeated .PhoneNumber phones = 4;</code>
   */
  java.util.List<? extends org.lab.arm.quarkus.grpc.model.PhoneNumberOrBuilder> 
      getPhonesOrBuilderList();
  /**
   * <code>repeated .PhoneNumber phones = 4;</code>
   */
  org.lab.arm.quarkus.grpc.model.PhoneNumberOrBuilder getPhonesOrBuilder(
      int index);

  /**
   * <code>int64 createdAt = 5;</code>
   * @return The createdAt.
   */
  long getCreatedAt();

  /**
   * <code>int64 updatedAt = 6;</code>
   * @return The updatedAt.
   */
  long getUpdatedAt();

  /**
   * <code>int64 deletedAt = 7;</code>
   * @return The deletedAt.
   */
  long getDeletedAt();
}