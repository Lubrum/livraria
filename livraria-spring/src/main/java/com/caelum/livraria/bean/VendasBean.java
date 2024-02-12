package com.caelum.livraria.bean;

import com.caelum.livraria.modelo.Venda;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Controller
public class VendasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager manager;

    public BarChartModel getVendasModel() {
        BarChartModel model = new BarChartModel();
        model.setAnimate(true);

        ChartSeries vendaSerie = new ChartSeries();
        vendaSerie.setLabel("Vendas 2016");

        List<Venda> vendas = getVendas();
        for (Venda venda : vendas) {
            vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
        }

        model.addSeries(vendaSerie);

        return model;
    }

    public List<Venda> getVendas() {
        return this.manager.createQuery("select v from Venda v", Venda.class).getResultList();
    }
}
