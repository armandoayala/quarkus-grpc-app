// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: project/person.proto

package org.lab.arm.quarkus.grpc.model;

public interface PhoneNumberOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PhoneNumber)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string number = 1;</code>
   * @return The number.
   */
  String getNumber();
  /**
   * <code>string number = 1;</code>
   * @return The bytes for number.
   */
  com.google.protobuf.ByteString
      getNumberBytes();

  /**
   * <code>.PhoneType type = 2;</code>
   * @return The enum numeric value on the wire for type.
   */
  int getTypeValue();
  /**
   * <code>.PhoneType type = 2;</code>
   * @return The type.
   */
  PhoneType getType();
}
