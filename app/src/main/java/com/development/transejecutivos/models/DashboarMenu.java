package com.development.transejecutivos.models;

import com.development.transejecutivos.R;

/**
 * Created by developer on 3/21/16.
 */
public class DashboarMenu {
    private String name;
    private int idDrawable;

    public DashboarMenu(String name, int idDrawable) {
        this.name = name;
        this.idDrawable = idDrawable;
    }

    public String getName() {
        return name;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return name.hashCode();
    }

    public static DashboarMenu[] ITEMS = {
            new DashboarMenu("Mi Perfil", R.drawable.profile),
            new DashboarMenu("Ver Servicios", R.drawable.services),
            new DashboarMenu("Buscar Servicio", R.drawable.search),
            new DashboarMenu("Solicitar Servicio", R.drawable.get_service),
            new DashboarMenu("Cerrar Sesión", R.drawable.logout),
    };

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return Coche
     */
    public static DashboarMenu getItem(int id) {
        for (DashboarMenu item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
