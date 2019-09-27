// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').dataTable()({
				    "language": {
				    	"sProcessing":     "Traitement en cours...",
				        "sSearch":         "Rechercher :",
				        "sLengthMenu":     "Afficher _MENU_ elements",
				        "sInfo":           "Affichage de l'element _START_ a _END_ sur _TOTAL_ elements",
				        "sInfoEmpty":      "Affichage de l'element 0 a 0 sur 0 element",
				        "sInfoFiltered":   "(filtre de _MAX_ elements au total)",
				        "sInfoPostFix":    "",
				        "sLoadingRecords": "Chargement en cours...",
				        "sZeroRecords":    "Aucun element a afficher",
				        "sEmptyTable":     "Aucune donnee disponible dans le tableau",
				        "oPaginate": {
				            "sFirst":      "Premier",
				            "sPrevious":   "Precedent",
				            "sNext":       "Suivant",
				            "sLast":       "Dernier"
				        },
				        "oAria": {
				            "sSortAscending":  ": activer pour trier la colonne par ordre croissant",
				            "sSortDescending": ": activer pour trier la colonne par ordre decroissant"
				        },
				        "select": {
				                "rows": {
				                    _: "%d lignes selectionnees",
				                    0: "Aucune ligne selectionnee",
				                    1: "1 ligne selectionnee"
				                } 
				        }
				    }
			  });
});
