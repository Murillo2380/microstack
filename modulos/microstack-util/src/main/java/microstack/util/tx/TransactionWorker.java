package microstack.util.tx;

import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.plastic.PlasticMethod;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;

public class TransactionWorker implements ComponentClassTransformWorker2 {
    private final MethodAdvice advice;

    public TransactionWorker(MethodAdvice advice) {
        this.advice = advice;
    }

    @Override
    public void transform(
            final PlasticClass plasticClass,
            TransformationSupport support,
            MutableComponentModel mcm) {

        for (PlasticMethod method: plasticClass.getMethodsWithAnnotation(Tx.class)) {
            method.addAdvice(advice);
        }
    }
}
