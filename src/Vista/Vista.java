package Vista;

import Modelo.Estados.TipodeSets;

public interface Vista {
  //  public void MostrarMano();
    public void MostrarMesa();
    public void MostrarSetsEnMesa();
    public void MostrarTurno();
    public void MostrarSelecciondeSet();
    public void MostrarSeleccionCarta();

    public void Imprimir(String string);
    public void MostrarTipoSet();
    public void MostrarCartasenMesa(String string);

}
