package fr.app.web.faces.interceptors;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import fr.app.web.faces.abstracts.AbstractModel;
import fr.app.web.faces.abstracts.GenericViewCRUD;
import fr.app.web.faces.interceptors.annotations.ElementTrace;
import fr.app.web.faces.interceptors.annotations.Trace;

@Trace
@Interceptor
public class TraceInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void catchInitException(final InvocationContext ctx) throws Exception {

		ctx.proceed();

	}

	private void contruireTraceAjout(AbstractModel<?> model) {

	}

	private void contruireTraceModification(AbstractModel<?> oldModel, AbstractModel<?> newModel) throws IllegalArgumentException, IllegalAccessException {

		for (Field field : newModel.getClass().getDeclaredFields()) {
			System.out.println("####### field : " + field.getName() + " #######");

			System.out.println("Annotations utilisables pour l'attribut : ");
			field.setAccessible(true);
			for (Annotation annotation : field.getAnnotations()) {
				System.out.println("\t ** " + annotation.annotationType().getName());

				if (annotation instanceof ElementTrace) {
					ElementTrace element = (ElementTrace) annotation;
					TraceElement traceElement = new TraceElement(element.libelle());
					
					traceElement.setAncienneValeur((String) field.get(oldModel));
					traceElement.setNouvelleValeur((String) field.get(newModel));
					
					String libelle = " [" + traceElement.getLibelle() + "] ";
					
					
					System.out.println("\t \t *** Nouvelle" + libelle + field.get(newModel) + "");
					System.out.println("\t \t *** Ancienne" + libelle + field.get(oldModel) + "");
					
				}

			}
			field.setAccessible(false);

		}
	}

	private void contruireTraceSuppression(AbstractModel<?> model) {

	}

	@AroundInvoke
	public Object catchException(final InvocationContext ctx) throws Throwable {
		System.out.println("TraceInterceptor catchException ");
		TraceMetier trace = new TraceMetier();

		// Récupération du controller
		GenericViewCRUD<?> controller = null;
		if (ctx.getTarget() instanceof GenericViewCRUD) {
			controller = (GenericViewCRUD<?>) ctx.getTarget();
		}

		// Récupération du nouveau modèle
		AbstractModel<?> newModel = (AbstractModel<?>) controller.getModel();
		
		// Récupération de l'ancien
		AbstractModel<?> oldModel = newModel.getClass().newInstance();
		for (AbstractModel<?> model :  controller.getListModels()) {
			if (model.getId().equals(newModel.getId())) {
				oldModel = model;
				System.out.println("[correspodance trouvée]");
			}
		}

		// Annotation trace sur la méthode
		Trace annotationTrace = ctx.getMethod().getAnnotation(Trace.class);
		if (Trace.Action.INSERT == annotationTrace.action()) {
			System.out.println("Début trace mode insert");
			trace.setAction("A");
			contruireTraceModification(oldModel, newModel);
		}
		
		
		


		
		

		return ctx.proceed();

	}

}
