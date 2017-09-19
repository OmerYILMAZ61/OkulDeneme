package okul.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import okul.bean.KullaniciBean;
import okul.entity.Rol;


@FacesConverter("RolConverter")
public class RolConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                KullaniciBean service = (KullaniciBean) fc.getExternalContext().getSessionMap().get("kullaniciBean");
                int id = Integer.parseInt(value);
                return  getMyRol(id, service.getRolList());
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid durum."));
            }
        }
        else {
            return null;
        }
    }
 
    private Rol getMyRol(int id, List<Rol> rolList) {
		for (Rol rol : rolList) {
			if(rol.getId() == id)
				return rol;
		}
		return null;
		
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Rol) object).getId());
        }
        else {
            return null;
        }
    }   
}         
