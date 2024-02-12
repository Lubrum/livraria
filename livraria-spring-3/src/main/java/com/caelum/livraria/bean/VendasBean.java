package com.caelum.livraria.bean;

import com.caelum.livraria.modelo.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.springframework.stereotype.Controller;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VendasBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager manager;

    public BarChartModel getVendasModel() {
        BarChartModel model = new BarChartModel();
        BarChartOptions options = new BarChartOptions();
        Animation animation = new Animation();
        animation.setDelay(1);
        animation.setDuration(1);
        animation.setEasing("");
        options.setAnimation(animation);
        model.setOptions(options);

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Vendas 2016");

        List<Venda> vendas = getVendas();
        List<String> titulos = new ArrayList<>();
        List<Number> quantidades = new ArrayList<>();
        for (Venda venda : vendas) {
            titulos.add(venda.getLivro().getTitulo());
            quantidades.add(venda.getQuantidade());
        }
        barDataSet.setData(quantidades);
        ChartData chartData = new ChartData();
        chartData.addChartDataSet(barDataSet);
        chartData.setLabels(titulos);
        model.setData(chartData);

        return model;
    }

    public List<Venda> getVendas() {
        return this.manager.createQuery("select v from Venda v", Venda.class).getResultList();
    }
}
