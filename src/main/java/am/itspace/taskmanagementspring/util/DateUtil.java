package am.itspace.taskmanagementspring.util;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DateUtil {

    public Date parsToDate(String dataStr) {
        return null;
    }

    public String parsToDate(Date date) {
        return "";
    }
}
