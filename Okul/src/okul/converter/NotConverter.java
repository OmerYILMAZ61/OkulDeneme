package okul.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import okul.bean.DersBean;
import okul.bean.NotBean;
import okul.entity.Dersler;
import okul.entity.Notlar;


@FacesConverter("NotConverter")
public class NotConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                NotBean service = (NotBean) fc.getExternalContext().getSessionMap().get("notBean");
                int id = Integer.parseInt(value);
                return  getMyNot(id, service.getNotlar());
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid durum."));
            }
        }
        else {
            return null;
        }
    }
 
    private Notlar getMyNot(int id, List<Notlar> notList) {
		for (Notlar not : notList) {
			if(not.getId() == id)
				return not;
		}
		return null;
		
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Notlar) object).getId());
        }
        else {
            return null;
        }
    }   
}         
