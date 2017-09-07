package control;

import static spark.Spark.*;

import modelo.Equipo;
import modelo.EquipoService;
import spark.Spark;
import util.JsonUtil;
import util.ResponseError;

public class EquipoController {

	public EquipoController(final EquipoService equipoService) {

		Spark.options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		Spark.before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
		});

		obtenerListaEquipos(equipoService);

		obtenerEquipo(equipoService);

		crearEquipo(equipoService);

		editarEquipo(equipoService);

		eliminarEquipo(equipoService);

		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			res.body(JsonUtil.toJson(new ResponseError(e)));
		});
	}

	private void crearEquipo(EquipoService equipoService) {
		post("/equipos", (req, res) -> {
			String nombre = req.queryParams("nombre");
			int fundacion = Integer.parseInt(req.queryParams("fundacion"));
			int estrellas = Integer.parseInt(req.queryParams("estrellas"));
			return equipoService.createEquipo(nombre, fundacion, estrellas);
		}, JsonUtil.json());
	}

	private void obtenerListaEquipos(EquipoService equipoService) {
		get("/equipos", (req, res) -> equipoService.getEquipos(), JsonUtil.json());

	}

	private void obtenerEquipo(EquipoService equipoService) {
		get("/equipos/:id", (req, res) -> {
			String id = req.params(":id");
			Equipo equipo = equipoService.getEquipo(id);
			if (equipo != null) {
				return equipo;
			}
			res.status(400);
			return new ResponseError("No se encontró equipo");
		}, JsonUtil.json());
	}

	private void editarEquipo(EquipoService equipoService) {
		// TODO Auto-generated method stub
		put("/equipos/:id", (req, res) -> {
			String nombre = req.queryParams("nombre");
			int fundacion = Integer.parseInt(req.queryParams("fundacion"));
			int estrellas = Integer.parseInt(req.queryParams("estrellas"));
			return equipoService.updateEquipo(req.params(":id"), nombre, fundacion, estrellas);
		}, JsonUtil.json());
	}

	private void eliminarEquipo(EquipoService equipoService) {
		// TODO Auto-generated method stub
		delete("/equipos/:id", (req, res) -> equipoService.deleteEquipo(req.params(":id")), JsonUtil.json());

	}

}
