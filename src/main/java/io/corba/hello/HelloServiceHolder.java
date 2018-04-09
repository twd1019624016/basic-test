package io.corba.hello;

/**
* hello/HelloServiceHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��HelloService.idl
* 2018��3��20�� ���ڶ� ����12ʱ35��25�� CST
*/

public final class HelloServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public HelloService value = null;

  public HelloServiceHolder ()
  {
  }

  public HelloServiceHolder (HelloService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HelloServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HelloServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HelloServiceHelper.type ();
  }

}
