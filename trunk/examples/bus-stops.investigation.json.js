> db.nodes.find({"tags.highway" : "bus_stop"}).limit(1)
{ "_id" : NumberLong(290107396), "location" : { "lat" : 50.4129503, "lon" : 30.5248045 }, "tags" : { "highway" : "bus_stop" } }

> db.ways.find({"nodes.$id" : 290107396})
{ "_id" : NumberLong(26472369), "nodes" : [
        {
                "$ref" : "nodes",
                "$id" : NumberLong(290107393)
        },
        {
                "$ref" : "nodes",
                "$id" : NumberLong(290107395)
        },
        {
                "$ref" : "nodes",
                "$id" : NumberLong(659612980)
        },
        {
                "$ref" : "nodes",
                "$id" : NumberLong(659612979)
        },
        {
                "$ref" : "nodes",
                "$id" : NumberLong(290107396)
        },
        {
                "$ref" : "nodes",
                "$id" : NumberLong(290107489)
        },
        {
                "$ref" : "nodes",
                "$id" : NumberLong(290107398)
        },
        {
                "$ref" : "nodes",
                "$id" : NumberLong(290107399)
        },
        {
                "$ref" : "nodes",
                "$id" : NumberLong(659612978)
        },
        {
                "$ref" : "nodes",
                "$id" : NumberLong(290107400)
        }
], "tags" : { "psv" : "designated", "bus" : "yes", "highway" : "service", "oneway" : "yes", "access" : "no" } }

> db.relations.find({"members.ref" : 26472369})
{ "_id" : NumberLong(1405268), "members" : [
        {
                "ref" : NumberLong(26524834),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(33547228),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(97947898),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(97947903),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(109651607),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(97947894),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(8389156),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(107414191),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(47155557),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(95932385),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(97947896),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(111763242),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(114009616),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(25518288),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(84039079),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(35399711),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(37726615),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(37726613),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(30907380),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(26472369),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(30907381),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(37837900),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(97947900),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(31154364),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(31235168),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(42489900),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(37837346),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(37837347),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(37837344),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(9451696),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(8069248),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(52058545),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(23118857),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(23118858),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(8082788),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(37920133),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(23120540),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(23120537),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(5180707),
                "type" : "way",
                "role" : "forward"
        },
        {
                "ref" : NumberLong(97947888),
                "type" : "way",
                "role" : "backward"
        },
        {
                "ref" : NumberLong(27514251),
                "type" : "way",
                "role" : ""
        },
        {
                "ref" : NumberLong(114798550),
                "type" : "way",
                "role" : ""
        },
        {
                "ref" : NumberLong(114798547),
                "type" : "way",
                "role" : ""
        },
        {
                "ref" : NumberLong(114798541),
                "type" : "way",
                "role" : ""
        },
        {
                "ref" : NumberLong(8142245),
                "type" : "way",
                "role" : ""
        },
        {
                "ref" : NumberLong(114798548),
                "type" : "way",
                "role" : ""
        },
        {
                "ref" : NumberLong(114798553),
                "type" : "way",
                "role" : ""
        },
        {
                "ref" : NumberLong(114798552),
                "type" : "way",
                "role" : ""
        },
        {
                "ref" : NumberLong(114227161),
                "type" : "way",
                "role" : ""
        }
], "tags" : { "description" : "¦Ü¦-TÀTÈTÀTÃTÂ¦-¦¦ TÂ¦-¦¦TÁTÖ òÄÖ172 (3): ¦ÜTÃ¦¬¦¦¦¦ ¦-TÀTÅTÖTÂ¦¦¦¦TÂTÃTÀ¦¬ TÂ¦- ¦¬¦-¦-TÃTÂTÃ -  ¦Û¦¦¦-TÖ¦-¦¦TÀ¦-¦+TÁTÌ¦¦¦- ¦¬¦¬¦-TÉ¦-", "route" : "bus", "name" : "¦Ü¦â172", "type" : "route" } }

