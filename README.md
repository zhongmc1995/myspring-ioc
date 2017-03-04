# myspring-ioc
自定义模拟spring ico
### xml格式
    <beans>
      <bean id="hello" class="com.zmc.test.Hello">
        <property name="str" value="hello world"></property>
      </bean>

      <bean id="hellos" class="com.zmc.test.Hellos">
        <property name="hello" ref="hello"></property>
      </bean>
    </beans>
