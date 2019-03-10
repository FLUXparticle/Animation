if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'js-test'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'js-test'.");
}
if (typeof js === 'undefined') {
  throw new Error("Error loading module 'js-test'. Its dependency 'js' was not found. Please, check whether 'js' is loaded prior to 'js-test'.");
}
this['js-test'] = function (_, Kotlin, $module$js) {
  'use strict';
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Unit = Kotlin.kotlin.Unit;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Bounds = $module$js.de.fluxparticle.animation.util.Bounds;
  var Color = $module$js.de.fluxparticle.animation.util.Color;
  var DynamicPoint2D_init = $module$js.de.fluxparticle.animation.point.DynamicPoint2D_init_lu1900$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var ElementObject = $module$js.de.fluxparticle.animation.elementobject.ElementObject;
  var Signal = $module$js.de.fluxparticle.animation.signal.Signal;
  var Box = $module$js.de.fluxparticle.animation.Box;
  var ElementRectangle = $module$js.de.fluxparticle.animation.elementobject.ElementRectangle;
  var GraphSolver = $module$js.de.fluxparticle.animation.graph.GraphSolver;
  var Pair = Kotlin.kotlin.Pair;
  var ensureNotNull = Kotlin.ensureNotNull;
  var ElementPath = $module$js.de.fluxparticle.animation.elementobject.ElementPath;
  var getValue = Kotlin.kotlin.collections.getValue_t9ocha$;
  var Graph = $module$js.de.fluxparticle.animation.graph.Graph;
  var GraphNode = $module$js.de.fluxparticle.animation.graph.GraphNode;
  var iterator = Kotlin.kotlin.js.iterator_s8jyvk$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var Clip = $module$js.de.fluxparticle.animation.Clip;
  var AnimationQueue = $module$js.de.fluxparticle.animation.AnimationQueue;
  var JsNodeCreator = $module$js.de.fluxparticle.animation.JsNodeCreator;
  var newSVGSVG = $module$js.de.fluxparticle.animation.newSVGSVG_dleff0$;
  function Algorithm() {
  }
  Algorithm.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Algorithm',
    interfaces: []
  };
  function BubbleSortAlgorithm() {
  }
  BubbleSortAlgorithm.prototype.render_mvkyax$ = function (animationQueue) {
    var $receiver = new BubbleSortBox(animationQueue);
    for (var top = $receiver.size() - 1 | 0; top >= 0; top--) {
      for (var i = 0; i < top; i++) {
        if (!$receiver.inOrder_za3lpa$(i)) {
          $receiver.swap_za3lpa$(i);
        }
      }
      $receiver.ready_za3lpa$(top);
    }
    return $receiver;
  };
  BubbleSortAlgorithm.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BubbleSortAlgorithm',
    interfaces: [Algorithm]
  };
  function Wrapper() {
    this.data = null;
  }
  Wrapper.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Wrapper',
    interfaces: []
  };
  var Array_0 = Array;
  function BubbleSortBox(animationQueue) {
    BubbleSortBox$Companion_getInstance();
    this.animationQueue_0 = animationQueue;
    this.array_0 = new Int32Array([13, 4, 3, 21, 1]);
    var array = Array_0(this.array_0.length);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      var $receiver = new ElementObject(this.animationQueue_0.time, true, true, 60, [this.array_0[i].toString()]);
      $receiver.translate = new Signal(this.animationQueue_0.time, BubbleSortBox$Companion_getInstance().center_0(i));
      array[i] = $receiver;
    }
    this.elementObjects_0 = array;
    this.rectangleCompare_0 = new Wrapper();
    this.rectangleReady_0 = new Wrapper();
    this.animationQueue_0.with_3gn2hk$(BubbleSortBox_init$lambda(this));
  }
  Object.defineProperty(BubbleSortBox.prototype, 'bounds', {
    get: function () {
      return new Bounds(0.0, 0.0, 10 + (this.array_0.length * 70 | 0) | 0, 60);
    }
  });
  BubbleSortBox.prototype.size = function () {
    return this.array_0.length;
  };
  function BubbleSortBox$inOrder$lambda(closure$index, this$BubbleSortBox, closure$result) {
    return function (aq, t) {
      var bs = BubbleSortBox$Companion_getInstance().surround_0(closure$index, 2);
      var colorCompare = Color.Companion.BLUE;
      var rectangle = createOrMove(this$BubbleSortBox.rectangleCompare_0, aq, t, bs, colorCompare);
      var c = closure$result ? Color.Companion.GREEN : Color.Companion.RED;
      aq.changeColor_bpsor1$(1, rectangle, c);
      return Unit;
    };
  }
  BubbleSortBox.prototype.inOrder_za3lpa$ = function (index) {
    var result = this.array_0[index] < this.array_0[index + 1 | 0];
    this.animationQueue_0.with_3gn2hk$(BubbleSortBox$inOrder$lambda(index, this, result));
    return result;
  };
  function BubbleSortBox$swap$lambda(this$BubbleSortBox, closure$index, closure$next) {
    return function (aq, f) {
      aq.move_cb4kqw$(0, this$BubbleSortBox.elementObjects_0[closure$index], BubbleSortBox$Companion_getInstance().center_0(closure$next), false, null, true);
      aq.move_cb4kqw$(0, this$BubbleSortBox.elementObjects_0[closure$next], BubbleSortBox$Companion_getInstance().center_0(closure$index), false, null, true);
      var tmp = this$BubbleSortBox.elementObjects_0[closure$index];
      this$BubbleSortBox.elementObjects_0[closure$index] = this$BubbleSortBox.elementObjects_0[closure$next];
      this$BubbleSortBox.elementObjects_0[closure$next] = tmp;
      return Unit;
    };
  }
  BubbleSortBox.prototype.swap_za3lpa$ = function (index) {
    var next = index + 1 | 0;
    var tmp = this.array_0[index];
    this.array_0[index] = this.array_0[next];
    this.array_0[next] = tmp;
    this.animationQueue_0.with_3gn2hk$(BubbleSortBox$swap$lambda(this, index, next));
  };
  function BubbleSortBox$ready$lambda(this$BubbleSortBox, closure$index, closure$range) {
    return function (aq, t) {
      var rectangle = this$BubbleSortBox.rectangleCompare_0.data;
      if (rectangle != null) {
        aq.fadeOut_bq1e3f$(-1, rectangle);
        this$BubbleSortBox.rectangleCompare_0.data = null;
      }
      var bs = BubbleSortBox$Companion_getInstance().surround_0(closure$index, closure$range);
      var colorReady = Color.Companion.GREEN;
      createOrMove(this$BubbleSortBox.rectangleReady_0, aq, t, bs, colorReady);
      return Unit;
    };
  }
  BubbleSortBox.prototype.ready_za3lpa$ = function (index) {
    var range = this.array_0.length - index | 0;
    this.animationQueue_0.with_3gn2hk$(BubbleSortBox$ready$lambda(this, index, range));
  };
  function BubbleSortBox$Companion() {
    BubbleSortBox$Companion_instance = this;
    this.WIDTH_0 = 60;
    this.HEIGHT_0 = 40;
    this.SPACING_0 = 10;
  }
  BubbleSortBox$Companion.prototype.center_0 = function (index) {
    return DynamicPoint2D_init(40 + (index * 70 | 0) | 0, 30);
  };
  BubbleSortBox$Companion.prototype.surround_0 = function (index, range) {
    return new Bounds(5 + (index * 70 | 0) | 0, 5, range * 70 | 0, 50);
  };
  BubbleSortBox$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var BubbleSortBox$Companion_instance = null;
  function BubbleSortBox$Companion_getInstance() {
    if (BubbleSortBox$Companion_instance === null) {
      new BubbleSortBox$Companion();
    }
    return BubbleSortBox$Companion_instance;
  }
  function BubbleSortBox_init$lambda(this$BubbleSortBox) {
    return function (aq, f) {
      var tmp$;
      tmp$ = this$BubbleSortBox.array_0;
      for (var i = 0; i !== tmp$.length; ++i) {
        aq.fadeIn_bq1e3f$(0, this$BubbleSortBox.elementObjects_0[i]);
      }
      return Unit;
    };
  }
  BubbleSortBox.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BubbleSortBox',
    interfaces: [Box]
  };
  function createOrMove($receiver, aq, t, bs, colorReady) {
    var rectangle = $receiver.data;
    if (rectangle == null) {
      var newRectangle = new ElementRectangle(t, bs, colorReady);
      aq.fadeIn_bq1e3f$(0, newRectangle);
      $receiver.data = newRectangle;
      return newRectangle;
    }
     else {
      aq.adjustAreaToBounds_8j0xwb$(0, rectangle, bs);
      aq.changeColor_bpsor1$(0, rectangle, colorReady);
      return rectangle;
    }
  }
  function DependencyAlgorithm(dependencyGraph) {
    this.dependencyGraph_0 = dependencyGraph;
  }
  DependencyAlgorithm.prototype.render_mvkyax$ = function (animationQueue) {
    return new DependencyBox(animationQueue, this.dependencyGraph_0);
  };
  DependencyAlgorithm.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DependencyAlgorithm',
    interfaces: [Algorithm]
  };
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var mapCapacity = Kotlin.kotlin.collections.mapCapacity_za3lpa$;
  var coerceAtLeast = Kotlin.kotlin.ranges.coerceAtLeast_dqglrj$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_bwtc7$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var UnsupportedOperationException_init = Kotlin.kotlin.UnsupportedOperationException_init_pdl1vj$;
  var Regex_init = Kotlin.kotlin.text.Regex_init_61zpoe$;
  var take = Kotlin.kotlin.collections.take_ba2ldo$;
  var emptyList = Kotlin.kotlin.collections.emptyList_287e2$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  function DependencyBox(animationQueue, dependencyGraph) {
    this.animationQueue_0 = animationQueue;
    this.dependencyGraph_0 = dependencyGraph;
    this.bounds_kbilfb$_0 = null;
    (new GraphSolver(this.dependencyGraph_0)).solve();
    var $receiver = this.dependencyGraph_0.nodes;
    var capacity = coerceAtLeast(mapCapacity(collectionSizeOrDefault($receiver, 10)), 16);
    var destination = LinkedHashMap_init(capacity);
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var tmp$_0 = destination.put_xwzc9p$;
      var x = (element.getLevel() * 60 | 0) * 2 | 0;
      var y = element.getPos();
      tmp$_0.call(destination, element, new Pair(x, y));
    }
    var centers = destination;
    var $receiver_0 = centers.values;
    var destination_0 = ArrayList_init(collectionSizeOrDefault($receiver_0, 10));
    var tmp$_1;
    tmp$_1 = $receiver_0.iterator();
    while (tmp$_1.hasNext()) {
      var item = tmp$_1.next();
      destination_0.add_11rb$(new Bounds(item.first - 30, item.second - 20, 60, 40));
    }
    var iterator = destination_0.iterator();
    if (!iterator.hasNext())
      throw UnsupportedOperationException_init("Empty collection can't be reduced.");
    var accumulator = iterator.next();
    while (iterator.hasNext()) {
      accumulator = accumulator.union_v7rwv9$(iterator.next());
    }
    this.bounds_kbilfb$_0 = accumulator;
    var destination_1 = LinkedHashMap_init(mapCapacity(centers.size));
    var tmp$_2;
    tmp$_2 = centers.entries.iterator();
    loop_label: while (tmp$_2.hasNext()) {
      var element_0 = tmp$_2.next();
      var tmp$_3 = destination_1.put_xwzc9p$;
      var tmp$_4 = element_0.key;
      var artifact = element_0.key;
      var center = element_0.value;
      var tmp$_5 = artifact.toString();
      var $receiver_1 = Regex_init('(?=\\p{Upper})').split_905azu$(tmp$_5, 0);
      var dropLastWhile$result;
      dropLastWhile$break: do {
        if (!$receiver_1.isEmpty()) {
          var iterator_0 = $receiver_1.listIterator_za3lpa$($receiver_1.size);
          while (iterator_0.hasPrevious()) {
            if (!(iterator_0.previous().length === 0)) {
              dropLastWhile$result = take($receiver_1, iterator_0.nextIndex() + 1 | 0);
              break dropLastWhile$break;
            }
          }
        }
        dropLastWhile$result = emptyList();
      }
       while (false);
      var lines = copyToArray(dropLastWhile$result);
      var elementObject = new ElementObject(this.animationQueue_0.time, true, true, 120, lines.slice());
      var translate = DynamicPoint2D_init(center.first - this.bounds.minX, center.second - this.bounds.minY);
      elementObject.setTranslate_20nywz$(translate);
      tmp$_3.call(destination_1, tmp$_4, elementObject);
    }
    var elementObjects = destination_1;
    var elementNodes = ArrayList_init_0();
    elementNodes.addAll_brywnq$(elementObjects.values);
    var tmp$_6;
    tmp$_6 = elementObjects.entries.iterator();
    while (tmp$_6.hasNext()) {
      var element_1 = tmp$_6.next();
      var artifact_0 = element_1.key;
      var fromObject = element_1.value;
      var tmp$_7;
      var fromCenter = fromObject.translate.lastValue();
      var from = DynamicPoint2D_init(fromCenter.x.value + 30, fromCenter.y.value);
      var dependencies = this.dependencyGraph_0.getNeighbours_dtm9gs$(artifact_0);
      tmp$_7 = dependencies.iterator();
      while (tmp$_7.hasNext()) {
        var dependency = tmp$_7.next();
        var toObject = ensureNotNull(elementObjects.get_11rb$(dependency));
        var toCenter = toObject.translate.lastValue();
        var to = DynamicPoint2D_init(toCenter.x.value - 30 - 5, toCenter.y.value);
        var path = new ElementPath(this.animationQueue_0.time, from, to);
        elementNodes.add_11rb$(path);
      }
    }
    this.animationQueue_0.with_3gn2hk$(DependencyBox_init$lambda(elementNodes));
  }
  Object.defineProperty(DependencyBox.prototype, 'bounds', {
    get: function () {
      return this.bounds_kbilfb$_0;
    }
  });
  function DependencyBox_init$lambda(closure$elementNodes) {
    return function (queue, f) {
      var tmp$;
      tmp$ = closure$elementNodes.iterator();
      while (tmp$.hasNext()) {
        var elementNode = tmp$.next();
        queue.fadeIn_bq1e3f$(0, elementNode);
      }
      return Unit;
    };
  }
  DependencyBox.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DependencyBox',
    interfaces: [Box]
  };
  var checkIndexOverflow = Kotlin.kotlin.collections.checkIndexOverflow_za3lpa$;
  function DependencyGraph(graphDescription) {
    this.dependencies_0 = null;
    this.artifacts_0 = null;
    var $receiver = graphDescription.keys;
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$, tmp$_0;
    var index = 0;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(new Artifact(item, checkIndexOverflow((tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0))));
    }
    var capacity = coerceAtLeast(mapCapacity(collectionSizeOrDefault(destination, 10)), 16);
    var destination_0 = LinkedHashMap_init(capacity);
    var tmp$_1;
    tmp$_1 = destination.iterator();
    while (tmp$_1.hasNext()) {
      var element = tmp$_1.next();
      destination_0.put_xwzc9p$(element.name, element);
    }
    var artifactsMap = destination_0;
    this.artifacts_0 = artifactsMap.values;
    var $receiver_0 = this.artifacts_0;
    var capacity_0 = coerceAtLeast(mapCapacity(collectionSizeOrDefault($receiver_0, 10)), 16);
    var destination_1 = LinkedHashMap_init(capacity_0);
    var tmp$_2;
    tmp$_2 = $receiver_0.iterator();
    while (tmp$_2.hasNext()) {
      var element_0 = tmp$_2.next();
      var tmp$_3 = destination_1.put_xwzc9p$;
      var tmp$_4 = element_0.id;
      var $receiver_1 = getValue(graphDescription, element_0.name);
      var destination_2 = ArrayList_init($receiver_1.length);
      var tmp$_5;
      for (tmp$_5 = 0; tmp$_5 !== $receiver_1.length; ++tmp$_5) {
        var item_0 = $receiver_1[tmp$_5];
        destination_2.add_11rb$(getValue(artifactsMap, item_0));
      }
      tmp$_3.call(destination_1, tmp$_4, destination_2);
    }
    this.dependencies_0 = destination_1;
  }
  Object.defineProperty(DependencyGraph.prototype, 'nodes', {
    get: function () {
      return this.artifacts_0;
    }
  });
  DependencyGraph.prototype.getNeighbours_dtm9gs$ = function (node) {
    return ensureNotNull(this.dependencies_0.get_11rb$(node.id));
  };
  DependencyGraph.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DependencyGraph',
    interfaces: [Graph]
  };
  function Artifact(name, id) {
    this.name = name;
    this.id_9kqlgj$_0 = id;
    this.level_0 = 0;
    this.pos_0 = 0;
    this.level_0 = 0;
    this.pos_0 = 0;
  }
  Object.defineProperty(Artifact.prototype, 'id', {
    get: function () {
      return this.id_9kqlgj$_0;
    }
  });
  Object.defineProperty(Artifact.prototype, 'preferredPos', {
    get: function () {
      return 0;
    }
  });
  Object.defineProperty(Artifact.prototype, 'size', {
    get: function () {
      return 50;
    }
  });
  Object.defineProperty(Artifact.prototype, 'isReferenced', {
    get: function () {
      return true;
    }
  });
  Artifact.prototype.getPos = function () {
    return this.pos_0;
  };
  Artifact.prototype.setPos_za3lpa$ = function (pos) {
    this.pos_0 = pos;
  };
  Artifact.prototype.getLevel = function () {
    return this.level_0;
  };
  Artifact.prototype.setLevel_za3lpa$ = function (level) {
    this.level_0 = level;
  };
  Artifact.prototype.toString = function () {
    return this.name;
  };
  Artifact.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Artifact',
    interfaces: [GraphNode]
  };
  function start$lambda$lambda$lambda(closure$time, closure$clip, closure$intervalId) {
    return function () {
      closure$time.v += 0.017;
      closure$clip.timeProperty().value = closure$time.v;
      if (closure$time.v > closure$clip.length) {
        window.clearInterval(closure$intervalId.v);
      }
      return Unit;
    };
  }
  function start$lambda(closure$algorithms, closure$time) {
    return function (it) {
      var $receiver = closure$algorithms;
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var closure$time_0 = closure$time;
        var tmp$_0;
        var intervalId = {v: null};
        var clip = new Clip();
        var animationQueue = new AnimationQueue(clip);
        var box = element.render_mvkyax$(animationQueue);
        var bounds = box.bounds;
        var nodes = animationQueue.getVisitedGroup_mhsj1b$(new JsNodeCreator());
        var $receiver_0 = newSVGSVG(bounds.width, bounds.height);
        var tmp$_1;
        tmp$_1 = nodes.iterator();
        while (tmp$_1.hasNext()) {
          var element_0 = tmp$_1.next();
          $receiver_0.appendChild(element_0);
        }
        var svg = $receiver_0;
        var div = document.createElement('div');
        div.appendChild(svg);
        (tmp$_0 = document.body) != null ? tmp$_0.appendChild(div) : null;
        var handler = start$lambda$lambda$lambda(closure$time_0, clip, intervalId);
        intervalId.v = window.setInterval(handler, 17);
      }
      return Unit;
    };
  }
  var LinkedHashMap_init_0 = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  function start(input) {
    var tmp$;
    var graphDescription = LinkedHashMap_init_0();
    tmp$ = iterator(input);
    while (tmp$.hasNext()) {
      var b = tmp$.next();
      var name = b.name;
      var deps = b.deps;
      graphDescription.put_xwzc9p$(name, deps);
    }
    var dependencyGraph = new DependencyGraph(graphDescription);
    var algorithms = listOf([new DependencyAlgorithm(dependencyGraph), new BubbleSortAlgorithm()]);
    var time = {v: 0.0};
    window.onload = start$lambda(algorithms, time);
  }
  var package$de = _.de || (_.de = {});
  var package$fluxparticle = package$de.fluxparticle || (package$de.fluxparticle = {});
  var package$animation = package$fluxparticle.animation || (package$fluxparticle.animation = {});
  var package$example = package$animation.example || (package$animation.example = {});
  package$example.Algorithm = Algorithm;
  package$example.BubbleSortAlgorithm = BubbleSortAlgorithm;
  package$example.DependencyAlgorithm = DependencyAlgorithm;
  package$example.DependencyGraph = DependencyGraph;
  package$example.Artifact = Artifact;
  package$example.start = start;
  Kotlin.defineModule('js-test', _);
  return _;
}(typeof this['js-test'] === 'undefined' ? {} : this['js-test'], kotlin, js);
