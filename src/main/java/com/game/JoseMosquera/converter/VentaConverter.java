package com.game.JoseMosquera.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.game.JoseMosquera.component.Logs;
import com.game.JoseMosquera.entity.Venta;
import com.game.JoseMosquera.model.VentaModel;

@Component("ventaConverter")
public class VentaConverter {

private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public VentaModel entity2model(Venta venta) {
		Logs.LOG.info("Llamada al metodo entity2model de la clase JuegoConverter, recibe una entidad: '"+venta.toString()+"'");
		VentaModel ventaModel = dozer.map(venta, VentaModel.class);
		Logs.LOG.info("Retorno del metodo entity2model de la clase JuegoConverter, devuelve un modelo: '"+ventaModel.toString()+"'");
		return ventaModel;
	}
	
	public Venta model2entity(VentaModel ventaModel) {
		Logs.LOG.info("Llamada al metodo model2entity de la clase JuegoConverter, recibe un modelo: '"+ventaModel.toString()+"'");
		Venta venta = dozer.map(ventaModel, Venta.class);
		Logs.LOG.info("Retorno del metodo model2entity de la clase JuegoConverter, devuelve una entidad: '"+venta.toString()+"'");
		return venta;
	}
}
