//=============================================================
// count all ways somehow related to current node
// using map-reduce
query = db.ways.find()

map = function() {
	var nodes = this.nodes;
	for(i=0;i<nodes.length;i++){
		emit(nodes[i].$id, {id:nodes[i].$id, count: 1});
	}
}

reduce = function(key, values) {
	var result = {id: key, count: 0};
    values.forEach(function(value) {
      result.count += value.count;
    });
    return result;
}

db.runCommand(
 { mapreduce : "ways",
   map : map,
   reduce : reduce
   , out: {replace : "relatedways"} 
   , verbose : true
 }
);

db.relatedways.find()

//=============================================================
// for each relation find all related bus stops
db.createCollection("currentrelations")

map = function() {
	var members = this.members;
	for(i=0;i<members.length;i++){
		if(members[i].type == "way"){
			//find way
			var wc = db.ways.find({"_id" : members[i].ref});
			if(!wc.hasNext()) continue;
			var nodes = wc.next().nodes;
			for(j=0;j<nodes.length;j++){
				// find all bust stop(!) nodes for this way:
				var nc = db.nodes.find({"_id" : nodes[j].$id, "tags.highway" : "bus_stop"})
				while(nc.hasNext()){
					var nd = nc.next()
					emit(this._id, {id:this._id, nodes: [nd._id], tags: this.tags});
				}
			}
		}
		if(members[i].type == "node"){
			//find node
			var nc = db.nodes.find({"_id" : members[i].ref, "tags.highway" : "bus_stop"});
			while(nc.hasNext()){
				var node = nc.next();
				emit(this._id, {id:this._id, nodes: [node._id], tags: this.tags});
			}
		}
	}
}

reduce = function(key, values) {
	var result = {id: key, nodes: [], tags: values[0].tags};
	var nodes = new Array();
    values.forEach(function(value) {
      //result.nodes.unshift(value.nodes);
	  value.nodes.forEach(function(v) {
		nodes.push(v)
	  });	  
    });
	result.nodes = nodes;
    return result;
}

enrichtags = function(key, value){
	var rc = db.relations.find({"_id" : value.id}).limit(1);
	print(key);
	//if(rc.hasNext()){
		//var r = rc.next();
		/*if(r.tags != undefined){
			var tags = r.tags;
			if(tags != undefined){
				value['tags'] = tags;
			}
		}*/
	//}
	return value;
}

db.runCommand(
 { mapreduce : "relations"
   , map : map
   , reduce : reduce
   , out: {replace : "currentrelations"} 
   //, finalize : enrichtags
   , verbose : true
 }
);

db.currentrelations.find()
//======== results ===========================================================================
{ "value" : { "id" : NumberLong(75641), "nodes" : [ NumberLong(382926749), NumberLong(382926751) ] } }
{ "value" : { "id" : NumberLong(177091), "nodes" : [ //14 trolleybus 
        NumberLong(546374270),
        NumberLong(385976073),
        NumberLong(546374272),
        NumberLong(373775995),
        NumberLong(546354908),
        NumberLong(546369307),
        NumberLong(546369310),
        NumberLong(546369320)
] } }
{ "value" : { "id" : NumberLong(372707), "nodes" : [ NumberLong(546374290) ] } }
{ "value" : { "id" : NumberLong(414022), "nodes" : [ NumberLong(1159208856) ] } }
{ "value" : { "id" : NumberLong(421762), "nodes" : [ NumberLong(546374270), NumberLong(385976073), NumberLong(546374272) ] } }
{ "value" : { "id" : NumberLong(421763), "nodes" : [
        NumberLong(373775995),
        NumberLong(546354908),
        NumberLong(546369307),
        NumberLong(546369310),
        NumberLong(546369320)
] } }
{ "value" : { "id" : NumberLong(421765), "nodes" : [ NumberLong(382926744) ] } }
{ "value" : { "id" : NumberLong(421767), "nodes" : [ NumberLong(382926742) ] } }
{ "value" : { "id" : NumberLong(421768), "nodes" : [ NumberLong(546369388) ] } }
{ "value" : { "id" : NumberLong(1415583), "nodes" : [ NumberLong(382926749), NumberLong(382926751) ] } }
//============================================================================================