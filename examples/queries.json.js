// get highway tag names
db.ways.find({"tags.highway" : {$exists : true}}, {"_id" : 0, "tags.highway" : 1})

// get distinct highway tag names
db.ways.distinct("tags.highway")
[
        "secondary",
        "unclassified",
        "track",
        "residential",
        "primary_link",
        "footway",
        "tertiary",
        "steps",
        "service",
        "primary",
        "living_street",
        "road",
        "trunk",
        "trunk_link",
        "pedestrian",
        "secondary_link",
        "living_street;residential",
        "construction",
        "path",
        "cycleway"
]

// bus_stop can only be tag on node
// get all bus stops
db.nodes.find({"tags.highway" : "bus_stop"})

// find way where corresponding node belongs to
db.ways.find({"nodes.$id" : NumberLong(290107396)}, {"_id" : 1})
bus stop: node(290107396) -> way(26472369) -> relation(1405268)
db.relations.find({"members.ref" : NumberLong(26472369)}, {"members.ref" : 1})


// get way where current node belongs to
db.ways.find({"nodes.$id" : NumberLong(290107396)})
// get all relations where current node belongs to
db.relations.find({"members.ref" : NumberLong(26472369)})

// distinct relation roles
> db.relations.distinct("members.role")
[
        "from",
        "to",
        "via",
        "house",
        "street",
        "",
        "backward",
        "forward",
        "outer",
        "address",
        "stop",
        "inner"
]

// streets (within nodes)
db.nodes.distinct("tags.addr:street")

// buses.. other transport routes
db.relations.find({"tags.route" : {$exists : true}})

//find way for stop
getWay = function(s){
	var cursor = db.ways.find({"nodes.$id" : s}, {"_id" : 1});
    return cursor.hasNext() ? cursor.next()._id : null;
}

getRelatedStops = function(stop){
	var ways_cursor = db.ways.find({"nodes.$id" : stop}, {"_id" : 1});
	if(!ways_cursor.hasNext()) return null;
    
	var way_id = ways_cursor.next()._id;
	var relations_cursor = db.relations.find({"members.ref" : way_id, "tags.type" : "street"}, {"members.ref" : 1});
	if(!relations_cursor.hasNext()) return null;
	
	// relation which describes current stop
	var relation = relations_cursor.next();
	
	var m = relation.members;
	for(i=0;i<m.length;i++){
		// way id
		//print(m[i].ref)
		var rel_way_cursor = db.ways.find({"_id" : m[i].ref});
		if(!rel_way_cursor.hasNext()) continue;
		var w = rel_way_cursor.next();
		print(w._id);
		//====================
		{
			var c = db.nodes.find({"_id": w.nodes.$ref,"tags.highway" : "bus_stop"});
			while(c.hasNext()){
				print("\t" + c.next()._id);
			}
		}
	}
}
//
db.system.js.save( { _id : "getRelatedStops" , value : function(stop){
	var ways_cursor = db.ways.find({"nodes.$id" : stop}, {"_id" : 1});
	if(!ways_cursor.hasNext()) return null;
    
	var way_id = ways_cursor.next()._id;
	var relations_cursor = db.relations.find({"members.ref" : way_id, "tags.type" : "street"}, {"members.ref" : 1});
	if(!relations_cursor.hasNext()) return null;
	
	// relation which describes current stop
	var relation = relations_cursor.next();
	
	var m = relation.members;
	for(i=0;i<m.length;i++){
		// way id
		//print(m[i].ref)
		var rel_way_cursor = db.ways.find({"_id" : m[i].ref});
		if(!rel_way_cursor.hasNext()) continue;
		var w = rel_way_cursor.next();
		print(w._id);
		//====================
		{
			var c = db.nodes.find({"_id": w.nodes.$ref,"tags.highway" : "bus_stop"});
			while(c.hasNext()){
				print("\t" + c.next()._id);
			}
		}
	}
} } );

getRelatedStops(290107396)

getNodeWith2Ways = function(node){
	var nodes_cursor = db.nodes.find({}, {"_id" : 1});
	while(nodes_cursor.hasNext()){
		var node_id = nodes_cursor.next()._id;
		var ways_cursor = db.ways.find({"nodes.$id" : node_id}, {"_id" : 1});
		var count = 0;
		while(ways_cursor.hasNext()){
			ways_cursor.next();
			count++;
		}
		if(count>1) print(node_id + ":\t" + count);
	}
}

getWayWith2Relations = function(node){
	var ways_cursor = db.ways.find({}, {"_id" : 1});
	while(ways_cursor.hasNext()){
		var way_id = ways_cursor.next()._id;
		var relations_cursor = db.relations.find({"members.ref" : way_id}, {"_id" : 1});
		var count = 0;
		while(relations_cursor.hasNext()){
			ways_cursor.relations_cursor();
			count++;
		}
		if(count>1) print(way_id + ":\t" + count);
	}
}

