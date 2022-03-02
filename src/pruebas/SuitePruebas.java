package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@SuiteDisplayName("Suite de pruebas")
@Suite
@SelectPackages({"pruebas"})
@SelectClasses({GestionUsuariosTest.class, GestionVideojuegosTest.class})
class SuitePruebas {

}
