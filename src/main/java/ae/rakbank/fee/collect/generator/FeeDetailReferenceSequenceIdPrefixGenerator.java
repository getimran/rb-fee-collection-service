package ae.rakbank.fee.collect.generator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

import static ae.rakbank.fee.collect.utility.AppConstants.FEE_REFERENCE_ID_SEQ_PREFIX;

/**
 * @author imran
 * Generator class for generating Fee ReferenceId with specified prefix
 */
public class FeeDetailReferenceSequenceIdPrefixGenerator extends
        SequenceStyleGenerator {

public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";

private String valuePrefix;

public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
public static final String NUMBER_FORMAT_DEFAULT = "%d";
private String numberFormat;

@Override
public Serializable generate(SharedSessionContractImplementor session,
                             Object object) throws HibernateException {
    return valuePrefix + String.format(numberFormat,
    super.generate(session, object));
}

@Override
public void configure(Type type, Properties params,
                      ServiceRegistry serviceRegistry) throws MappingException {
    super.configure(LongType.INSTANCE, params, serviceRegistry);
    valuePrefix = 
ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER,
            params, FEE_REFERENCE_ID_SEQ_PREFIX);
    numberFormat = 
ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER,
            params, NUMBER_FORMAT_DEFAULT);
}

}