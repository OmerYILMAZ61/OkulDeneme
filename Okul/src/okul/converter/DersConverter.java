package okul.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import okul.bean.DersBean;
import okul.entity.Dersler;


@FacesConverter("DersConverter")
public class DersConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                DersBean service = (DersBean) fc.getExternalContext().getSessionMap().get("dersBean");
                int id = Integer.parseInt(value);
                return  getMyDers(id, service.getDersList());
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid durum."));
            }
        }
        else {
            return null;
        }
    }
 
    private Dersler getMyDers(int id, List<Dersler> dersList) {
		for (Dersler ders : dersList) {
			if(ders.getId() == id)
				return ders;
		}
		return null;
		
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Dersler) object).getId());
        }
        else {
            return null;
        }
    }   
}         
