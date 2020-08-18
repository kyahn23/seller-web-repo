package com.pentas.sellerweb.common.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import lombok.extern.slf4j.Slf4j;

/*
<tx:annotation-driven transaction-manager="txManager"/>
	<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	<tx:advice id="txAdvice" transaction-manager="txManager">
		<tx:attributes>
			<tx:method name="retrieve*" read-only="true" />
			<tx:method name="select*" read-only="true" />
			<tx:method name="insert*" rollback-for="Exception" />
			<tx:method name="update*" rollback-for="Exception" />
			<tx:method name="delete*" rollback-for="Exception" />
			<tx:method name="save*" rollback-for="Exception" />
			<tx:method name="process*" propagation="REQUIRES_NEW" rollback-for="Exception" />
		</tx:attributes>
	</tx:advice>
	<aop:config>
		<aop:pointcut id="requiredTx" expression="execution(* com.pentas.service.*Service.*(..))"/>
		<aop:advisor advice-ref="txAdvice" pointcut-ref="requiredTx" />
	</aop:config>
</beans>
*/
@Aspect
@Configuration
@Slf4j
public class TransactionAopConfig {

//    private static final int TX_METHOD_TIMEOUT = 10;
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.pentas.service.*Service.*(..))";

    @Autowired
    private DataSourceTransactionManager transactionManager;
    
    @Bean
    public TransactionInterceptor txAdvice() {
    	TransactionInterceptor txAdvice = new TransactionInterceptor();
    	Properties txAttributes = new Properties();    	

    	List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
    	rollbackRules.add(new RollbackRuleAttribute(Exception.class));

    	/** If need to add additionall exceptio, add here **/    	

//    	DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
//    	readOnlyAttribute.setReadOnly(true);
//    	readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);    	

        RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
//        writeAttribute.setTimeout(TX_METHOD_TIMEOUT);
        
//		String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
		String writeTransactionAttributesDefinition = writeAttribute.toString();
//		log.info("Read Only Attributes :: {}", readOnlyTransactionAttributesDefinition);
//		log.info("Write Attributes :: {}", writeTransactionAttributesDefinition);

		// read-only
//		txAttributes.setProperty("retrieve*", readOnlyTransactionAttributesDefinition);
//		txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
//		txAttributes.setProperty("get*", readOnlyTransactionAttributesDefinition);
//		txAttributes.setProperty("list*", readOnlyTransactionAttributesDefinition);
//		txAttributes.setProperty("search*", readOnlyTransactionAttributesDefinition);
//		txAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);
//		txAttributes.setProperty("count*", readOnlyTransactionAttributesDefinition);		

		// write rollback-rule
		txAttributes.setProperty("*", writeTransactionAttributesDefinition);		

		txAdvice.setTransactionAttributes(txAttributes);
		txAdvice.setTransactionManager(transactionManager);

        return txAdvice;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("(execution(* *..*.service..*.*(..)) || execution(* *..*.services..*.*(..)))");
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
