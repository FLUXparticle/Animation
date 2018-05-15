if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'js'.");
}
var js = function (_, Kotlin) {
  'use strict';
  var getCallableRef = Kotlin.getCallableRef;
  var Unit = Kotlin.kotlin.Unit;
  var HashMap_init = Kotlin.kotlin.collections.HashMap_init_q3lmfv$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var ensureNotNull = Kotlin.ensureNotNull;
  var throwUPAE = Kotlin.throwUPAE;
  var toString = Kotlin.toString;
  var toList = Kotlin.kotlin.collections.toList_7wnvza$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var L0 = Kotlin.Long.ZERO;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var contains = Kotlin.kotlin.text.contains_li3zpu$;
  var lastIndexOf = Kotlin.kotlin.text.lastIndexOf_8eortd$;
  var toMutableSet = Kotlin.kotlin.collections.toMutableSet_7wnvza$;
  var HashSet_init = Kotlin.kotlin.collections.HashSet_init_287e2$;
  var equals = Kotlin.equals;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var StringBuilder = Kotlin.kotlin.text.StringBuilder;
  var IllegalArgumentException_init = Kotlin.kotlin.IllegalArgumentException_init_pdl1vj$;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mutableMapOf = Kotlin.kotlin.collections.mutableMapOf_qfcya0$;
  var Pair = Kotlin.kotlin.Pair;
  var numberToInt = Kotlin.numberToInt;
  var NoSuchElementException_init = Kotlin.kotlin.NoSuchElementException_init;
  var throwCCE = Kotlin.throwCCE;
  ElementObject.prototype = Object.create(ElementNode.prototype);
  ElementObject.prototype.constructor = ElementObject;
  ElementPath.prototype = Object.create(ElementNode.prototype);
  ElementPath.prototype.constructor = ElementPath;
  ElementRectangle.prototype = Object.create(ElementNode.prototype);
  ElementRectangle.prototype.constructor = ElementRectangle;
  SignalConstant.prototype = Object.create(SignalFunction.prototype);
  SignalConstant.prototype.constructor = SignalConstant;
  SignalCurve$Builder.prototype = Object.create(SignalPath$Builder.prototype);
  SignalCurve$Builder.prototype.constructor = SignalCurve$Builder;
  SignalCurve.prototype = Object.create(SignalPath.prototype);
  SignalCurve.prototype.constructor = SignalCurve;
  SignalDoubleLine$Builder.prototype = Object.create(SignalPath$Builder.prototype);
  SignalDoubleLine$Builder.prototype.constructor = SignalDoubleLine$Builder;
  SignalDoubleLine.prototype = Object.create(SignalPath.prototype);
  SignalDoubleLine.prototype.constructor = SignalDoubleLine;
  SignalInterpolatable$Builder.prototype = Object.create(SignalPath$Builder.prototype);
  SignalInterpolatable$Builder.prototype.constructor = SignalInterpolatable$Builder;
  SignalInterpolatable.prototype = Object.create(SignalPath.prototype);
  SignalInterpolatable.prototype.constructor = SignalInterpolatable;
  SignalJump$Builder.prototype = Object.create(SignalPath$Builder.prototype);
  SignalJump$Builder.prototype.constructor = SignalJump$Builder;
  SignalJump.prototype = Object.create(SignalPath.prototype);
  SignalJump.prototype.constructor = SignalJump;
  SignalTransform.prototype = Object.create(SignalFunction.prototype);
  SignalTransform.prototype.constructor = SignalTransform;
  SimpleValue.prototype = Object.create(Value.prototype);
  SimpleValue.prototype.constructor = SimpleValue;
  MappedValue.prototype = Object.create(Value.prototype);
  MappedValue.prototype.constructor = MappedValue;
  CombinedValue.prototype = Object.create(Value.prototype);
  CombinedValue.prototype.constructor = CombinedValue;
  EmptyChain.prototype = Object.create(Chain.prototype);
  EmptyChain.prototype.constructor = EmptyChain;
  EagerChain.prototype = Object.create(Chain.prototype);
  EagerChain.prototype.constructor = EagerChain;
  ConcatChain.prototype = Object.create(Chain.prototype);
  ConcatChain.prototype.constructor = ConcatChain;
  function AnimationCollection() {
    this.animations_0 = HashMap_init();
  }
  AnimationCollection.prototype.add_1didyy$ = function (priority, Timeframe) {
    var $receiver = this.animations_0;
    var tmp$;
    var value = $receiver.get_11rb$(priority);
    if (value == null) {
      var answer = ArrayList_init();
      $receiver.put_xwzc9p$(priority, answer);
      tmp$ = answer;
    }
     else {
      tmp$ = value;
    }
    tmp$.add_11rb$(Timeframe);
  };
  function AnimationCollection$appendToClip$lambda(closure$values, closure$clip) {
    return function () {
      var $receiver = closure$values;
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        closure$clip.addTimeframe_jqredg$(element);
      }
      return Unit;
    };
  }
  AnimationCollection.prototype.appendToClip_dbs3kc$ = function (clip) {
    var tmp$;
    var sortedValues = this.sortedValues_0();
    tmp$ = sortedValues.iterator();
    while (tmp$.hasNext()) {
      var values = tmp$.next();
      clip.invoke_o14v8n$(AnimationCollection$appendToClip$lambda(values, clip));
    }
  };
  AnimationCollection.prototype.appendToClipRaw_dbs3kc$ = function (clip) {
    var tmp$;
    var sortedValues = this.sortedValues_0();
    tmp$ = sortedValues.iterator();
    while (tmp$.hasNext()) {
      var values = tmp$.next();
      var tmp$_0;
      tmp$_0 = values.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        clip.addTimeframe_jqredg$(element);
      }
    }
  };
  function AnimationCollection$sortedValues$lambda(entry) {
    return entry.key;
  }
  var sortedWith = Kotlin.kotlin.collections.sortedWith_eknfly$;
  var wrapFunction = Kotlin.wrapFunction;
  var compareBy$lambda = wrapFunction(function () {
    var compareValues = Kotlin.kotlin.comparisons.compareValues_s00gnj$;
    return function (closure$selector) {
      return function (a, b) {
        var selector = closure$selector;
        return compareValues(selector(a), selector(b));
      };
    };
  });
  var Comparator = Kotlin.kotlin.Comparator;
  function Comparator$ObjectLiteral(closure$comparison) {
    this.closure$comparison = closure$comparison;
  }
  Comparator$ObjectLiteral.prototype.compare = function (a, b) {
    return this.closure$comparison(a, b);
  };
  Comparator$ObjectLiteral.$metadata$ = {kind: Kind_CLASS, interfaces: [Comparator]};
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  AnimationCollection.prototype.sortedValues_0 = function () {
    var $receiver = sortedWith(this.animations_0.entries, new Comparator$ObjectLiteral(compareBy$lambda(AnimationCollection$sortedValues$lambda)));
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(item.value);
    }
    return destination;
  };
  AnimationCollection.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AnimationCollection',
    interfaces: []
  };
  function fadeIn(node) {
    var seOpacity = new SignalEvent(node.opacity, 1.0, new SignalDoubleLine$Builder());
    return new Timeframe(1.0, [seOpacity]);
  }
  function show(node) {
    var seOpacity = new SignalEvent(node.opacity, 1.0, new SignalDoubleLine$Builder());
    return new Timeframe(0.0, [seOpacity]);
  }
  function fadeOut(node) {
    var seOpacity = new SignalEvent(node.opacity, 0.0, new SignalDoubleLine$Builder());
    return new Timeframe(1.0, [seOpacity]);
  }
  function hide(node) {
    var seOpacity = new SignalEvent(node.opacity, 0.0, new SignalDoubleLine$Builder());
    return new Timeframe(0.0, [seOpacity]);
  }
  function move(node, dynDest, simple) {
    var seTranslate;
    if (simple) {
      seTranslate = new SignalEvent(node.translate, dynDest, new SignalInterpolatable$Builder());
    }
     else {
      seTranslate = new SignalEvent(node.translate, dynDest, new SignalCurve$Builder());
    }
    return new Timeframe(simple ? 1 : 2, [seTranslate]);
  }
  function adjustAreaToBounds(area, bounds) {
    var tmp$;
    if ((tmp$ = area.boundsInParent) != null ? tmp$.equals(bounds) : null) {
      return null;
    }
    var time;
    var lastX = area.x.lastValue();
    var lastY = area.y.lastValue();
    var lastWidth = area.width.lastValue();
    var lastHeight = area.height.lastValue();
    if ((lastX === bounds.minX || lastWidth === bounds.width) && (lastY === bounds.minY || lastHeight === bounds.height)) {
      time = 1.0;
    }
     else {
      time = 2.0;
    }
    var seX = new SignalEvent(area.x, bounds.minX, new SignalDoubleLine$Builder());
    var seY = new SignalEvent(area.y, bounds.minY, new SignalDoubleLine$Builder());
    var seWidth = new SignalEvent(area.width, bounds.width, new SignalDoubleLine$Builder());
    var seHeight = new SignalEvent(area.height, bounds.height, new SignalDoubleLine$Builder());
    return new Timeframe(time, [seX, seY, seWidth, seHeight]);
  }
  function changeColor(area, toColor) {
    var seColor = new SignalEvent(area.fill, toColor, new SignalInterpolatable$Builder());
    return new Timeframe(1.0, [seColor]);
  }
  function update(obj, newContent) {
    var seContent = new SignalEvent(ensureNotNull(obj.contentLine), newContent, new SignalJump$Builder());
    return new Timeframe(0.1, [seContent]);
  }
  function AnimationQueue(clip) {
    AnimationQueue$Companion_getInstance();
    this.clip = clip;
    this.group_0 = ArrayList_init();
    this.transitions_vnden5$_0 = this.transitions_vnden5$_0;
  }
  Object.defineProperty(AnimationQueue.prototype, 'transitions_0', {
    get: function () {
      if (this.transitions_vnden5$_0 == null)
        return throwUPAE('transitions');
      return this.transitions_vnden5$_0;
    },
    set: function (transitions) {
      this.transitions_vnden5$_0 = transitions;
    }
  });
  Object.defineProperty(AnimationQueue.prototype, 'time', {
    get: function () {
      return this.clip.timeProperty();
    }
  });
  AnimationQueue.prototype.with_3gn2hk$ = function (consumer) {
    this.begin();
    consumer(this, this.time);
    this.end();
  };
  AnimationQueue.prototype.begin = function () {
    AnimationQueue$Companion_getInstance().LOG1_0.info_61zpoe$('{');
    this.transitions_0 = new AnimationCollection();
  };
  AnimationQueue.prototype.add_bq1e3f$ = function (priority, obj) {
    AnimationQueue$Companion_getInstance().LOG2_0.info_61zpoe$('ADD ' + toString(obj));
    this.group_0.add_11rb$(obj);
    this.transitions_0.add_1didyy$(priority, show(obj));
  };
  AnimationQueue.prototype.fadeIn_bq1e3f$ = function (priority, obj) {
    AnimationQueue$Companion_getInstance().LOG2_0.info_61zpoe$('FADE_IN ' + toString(obj));
    AnimationQueue$Companion_getInstance().LOG3_0.info_61zpoe$('ADD ' + toString(obj));
    this.transitions_0.add_1didyy$(priority, fadeIn(obj));
    if (Kotlin.isType(obj, ElementRectangle)) {
      this.group_0.add_wxm5ur$(0, obj);
    }
     else {
      this.group_0.add_11rb$(obj);
    }
  };
  AnimationQueue.prototype.changeColor_bpsor1$ = function (priority, area, toColor) {
    AnimationQueue$Companion_getInstance().LOG2_0.info_61zpoe$('CHANGE_COLOR ' + toString(area));
    this.transitions_0.add_1didyy$(priority, changeColor(area, toColor));
  };
  AnimationQueue.prototype.adjustAreaToBounds_8j0xwb$ = function (priority, area, bounds) {
    AnimationQueue$Companion_getInstance().LOG2_0.info_61zpoe$('CHANGE_AREA ' + toString(area));
    var timeframe = adjustAreaToBounds(area, bounds);
    if (timeframe != null) {
      this.transitions_0.add_1didyy$(priority, timeframe);
      var lastY = area.y.lastValue();
      return lastY !== bounds.minY;
    }
    return false;
  };
  AnimationQueue.prototype.move_cb4kqw$ = function (priority, obj, dest, copy, oldObject, simple) {
    AnimationQueue$Companion_getInstance().LOG2_0.info_61zpoe$('MOVE ' + toString(obj));
    if (copy) {
      AnimationQueue$Companion_getInstance().LOG3_0.info_61zpoe$('ADD (COPY) ' + toString(obj));
      this.group_0.add_11rb$(obj);
      this.transitions_0.add_1didyy$(priority, show(obj));
    }
    this.transitions_0.add_1didyy$(priority, move(obj, dest, simple));
    if (oldObject != null) {
      this.transitions_0.add_1didyy$(priority + 1 | 0, hide(oldObject));
    }
  };
  AnimationQueue.prototype.fadeOut_bq1e3f$ = function (priority, obj) {
    AnimationQueue$Companion_getInstance().LOG2_0.info_61zpoe$('FADE_OUT ' + toString(obj));
    this.transitions_0.add_1didyy$(priority, fadeOut(obj));
  };
  AnimationQueue.prototype.update_jczp1o$ = function (priority, obj, newContent) {
    this.transitions_0.add_1didyy$(priority, update(obj, newContent));
  };
  AnimationQueue.prototype.end = function () {
    this.transitions_0.appendToClip_dbs3kc$(this.clip);
    AnimationQueue$Companion_getInstance().LOG1_0.info_61zpoe$('}');
  };
  AnimationQueue.prototype.endRaw = function () {
    this.transitions_0.appendToClipRaw_dbs3kc$(this.clip);
    AnimationQueue$Companion_getInstance().LOG1_0.info_61zpoe$('}');
  };
  AnimationQueue.prototype.getGroup = function () {
    return toList(this.group_0);
  };
  AnimationQueue.prototype.getVisitedGroup_mhsj1b$ = function (visitor) {
    var tmp$;
    var result = ArrayList_init(this.group_0.size);
    tmp$ = this.group_0.iterator();
    while (tmp$.hasNext()) {
      var elementNode = tmp$.next();
      var node = elementNode.accept_mhsj1b$(visitor);
      result.add_11rb$(node);
    }
    return result;
  };
  function AnimationQueue$Companion() {
    AnimationQueue$Companion_instance = this;
    this.LOG1_0 = Log$Companion_getInstance().getLog_za3lpa$(1);
    this.LOG2_0 = Log$Companion_getInstance().getLog_za3lpa$(2);
    this.LOG3_0 = Log$Companion_getInstance().getLog_za3lpa$(3);
  }
  AnimationQueue$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var AnimationQueue$Companion_instance = null;
  function AnimationQueue$Companion_getInstance() {
    if (AnimationQueue$Companion_instance === null) {
      new AnimationQueue$Companion();
    }
    return AnimationQueue$Companion_instance;
  }
  AnimationQueue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AnimationQueue',
    interfaces: []
  };
  function Box() {
  }
  Box.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Box',
    interfaces: []
  };
  function Clip() {
    this.time = new SimpleValue(0.0);
    this.clipTicks = L0;
    this.ticksBegin_p7gyxx$_0 = L0;
    this.keyframes = mutableListOf([L0]);
  }
  Object.defineProperty(Clip.prototype, 'length', {
    get: function () {
      return toSeconds(this.clipTicks);
    }
  });
  Clip.prototype.timeProperty = function () {
    return this.time;
  };
  Clip.prototype.invoke_o14v8n$ = function (runnable) {
    this.ticksBegin_p7gyxx$_0 = this.clipTicks;
    runnable();
    this.keyframes.add_11rb$(this.clipTicks);
  };
  Clip.prototype.addTimeframe_jqredg$ = function (timeframe) {
    var tmp$;
    var duration = timeframe.duration;
    tmp$ = timeframe.signalEvents.iterator();
    while (tmp$.hasNext()) {
      var signalEvent = tmp$.next();
      this.addSignalEvents_aaf6tx$_0(duration, signalEvent);
    }
  };
  Clip.prototype.addSignalEvents_aaf6tx$_0 = function (duration, signalEvent) {
    var ticksStart = this.ticksBegin_p7gyxx$_0;
    var ticksDuration = toTicks(duration);
    var ticksEnd = ticksStart.add(ticksDuration);
    var signal = signalEvent.signal;
    signal.changeValue_sx9fpw$(signalEvent.pathBuilder, signalEvent.value, ticksStart, ticksDuration);
    if (ticksEnd.compareTo_11rb$(this.clipTicks) > 0) {
      this.clipTicks = ticksEnd;
    }
  };
  Clip.prototype.getKeyframeList = function () {
    return toList(this.keyframes);
  };
  Clip.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Clip',
    interfaces: []
  };
  function Timeframe(duration, signalEvents) {
    this.duration = duration;
    this.signalEvents = null;
    this.signalEvents = listOf(signalEvents.slice());
  }
  Timeframe.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Timeframe',
    interfaces: []
  };
  function ElementNode(time) {
    this.opacity = new Signal(time, 0.0);
  }
  ElementNode.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ElementNode',
    interfaces: []
  };
  function ElementNodeVisitor() {
  }
  ElementNodeVisitor.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ElementNodeVisitor',
    interfaces: []
  };
  function ElementObject(time, isUseRect, isCenter, width, lines) {
    ElementObject$Companion_getInstance();
    ElementNode.call(this, time);
    this.time_0 = time;
    this.isUseRect = isUseRect;
    this.isCenter = isCenter;
    this.width = width;
    this.headLine = null;
    this.contentLine = null;
    this.translate = new Signal(this.time_0, DynamicPoint2D_init_0(0.0, 0.0));
    this.circle = null;
    var firstLine = ensureNotNull(lines[0]);
    if (contains(firstLine, '.')) {
      var startIndex = lastIndexOf(firstLine, 46) + 1 | 0;
      this.headLine = firstLine.substring(startIndex);
    }
     else {
      this.headLine = firstLine;
    }
    if (lines.length > 1) {
      var secondLine = lines[1];
      if (secondLine != null) {
        this.contentLine = new Signal(this.time_0, secondLine);
        this.circle = null;
      }
       else {
        this.contentLine = null;
        var midY = (80 / (lines.length + 1 | 0) | 0) - 20 | 0;
        this.circle = DynamicPoint2D_init_0(0.0, midY);
      }
    }
     else {
      this.contentLine = null;
      this.circle = null;
    }
  }
  ElementObject.prototype.setTranslate_20nywz$ = function (translate) {
    this.translate = new Signal(this.time_0, translate);
  };
  ElementObject.prototype.accept_mhsj1b$ = function (visitor) {
    return visitor.visitObject_kkvjv8$(this);
  };
  function ElementObject$Companion() {
    ElementObject$Companion_instance = this;
    this.MARGIN = 10;
    this.HEIGHT = 40;
    this.OPERAND_WIDTH = 60;
    this.SYMBOL_WIDTH = 40;
    this.FUNCTION_WIDTH = 180;
    this.OBJECT_WIDTH = 120;
  }
  ElementObject$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var ElementObject$Companion_instance = null;
  function ElementObject$Companion_getInstance() {
    if (ElementObject$Companion_instance === null) {
      new ElementObject$Companion();
    }
    return ElementObject$Companion_instance;
  }
  ElementObject.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ElementObject',
    interfaces: [ElementNode]
  };
  function ElementPath(time, origin, dest) {
    ElementNode.call(this, time);
    this.origin = origin;
    this.dest = dest;
  }
  ElementPath.prototype.accept_mhsj1b$ = function (visitor) {
    return visitor.visitPath_eorgsy$(this);
  };
  ElementPath.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ElementPath',
    interfaces: [ElementNode]
  };
  function ElementRectangle(time, bs, fill) {
    ElementNode.call(this, time);
    this.x = new Signal(time, bs.minX);
    this.y = new Signal(time, bs.minY);
    this.width = new Signal(time, bs.width);
    this.height = new Signal(time, bs.height);
    this.fill = new Signal(time, fill);
  }
  Object.defineProperty(ElementRectangle.prototype, 'boundsInParent', {
    get: function () {
      return new Bounds(this.x.lastValue(), this.y.lastValue(), this.width.lastValue(), this.height.lastValue());
    }
  });
  ElementRectangle.prototype.accept_mhsj1b$ = function (visitor) {
    return visitor.visitRectangle_f3mfoi$(this);
  };
  ElementRectangle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ElementRectangle',
    interfaces: [ElementNode]
  };
  function Graph() {
  }
  Graph.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Graph',
    interfaces: []
  };
  var compareBy$lambda_0 = wrapFunction(function () {
    var compareValues = Kotlin.kotlin.comparisons.compareValues_s00gnj$;
    return function (closure$selector) {
      return function (a, b) {
        var selector = closure$selector;
        return compareValues(selector(a), selector(b));
      };
    };
  });
  function Comparator$ObjectLiteral_0(closure$comparison) {
    this.closure$comparison = closure$comparison;
  }
  Comparator$ObjectLiteral_0.prototype.compare = function (a, b) {
    return this.closure$comparison(a, b);
  };
  Comparator$ObjectLiteral_0.$metadata$ = {kind: Kind_CLASS, interfaces: [Comparator]};
  function GraphLevelSolver(nodes) {
    this.nodes_0 = sortedWith(nodes, new Comparator$ObjectLiteral_0(compareBy$lambda_0(GraphLevelSolver$nodes$lambda)));
    this.maxK_0 = 0;
    this.min_0 = 0;
    this.max_0 = 0;
    var tmp$;
    var maxK = 0;
    tmp$ = nodes.iterator();
    while (tmp$.hasNext()) {
      var graphNode = tmp$.next();
      maxK = maxK + graphNode.size | 0;
    }
    this.maxK_0 = maxK;
    this.min_0 = nodes.get_za3lpa$(0).preferredPos - maxK | 0;
    this.max_0 = nodes.get_za3lpa$(nodes.size - 1 | 0).preferredPos + maxK | 0;
  }
  function GraphLevelSolver$Range(min, max) {
    this.min_0 = min;
    this.max_0 = max;
  }
  Object.defineProperty(GraphLevelSolver$Range.prototype, 'isEmpty', {
    get: function () {
      return this.min_0 > this.max_0;
    }
  });
  Object.defineProperty(GraphLevelSolver$Range.prototype, 'center', {
    get: function () {
      return (this.min_0 + this.max_0 | 0) / 2 | 0;
    }
  });
  GraphLevelSolver$Range.prototype.getMin = function () {
    return this.min_0;
  };
  GraphLevelSolver$Range.prototype.setMin_za3lpa$ = function (min) {
    if (min > this.min_0) {
      this.min_0 = min;
    }
  };
  GraphLevelSolver$Range.prototype.getMax = function () {
    return this.max_0;
  };
  GraphLevelSolver$Range.prototype.setMax_za3lpa$ = function (max) {
    if (max < this.max_0) {
      this.max_0 = max;
    }
  };
  GraphLevelSolver$Range.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Range',
    interfaces: []
  };
  GraphLevelSolver.prototype.solve = function () {
    var k = this.findK_0();
    var ranges = this.mkRanges_0(k);
    for (var i = 0; i !== ranges.length; ++i) {
      this.nodes_0.get_za3lpa$(i).setPos_za3lpa$(ranges[i].center);
    }
  };
  GraphLevelSolver.prototype.findK_0 = function () {
    var lo = 0;
    var hi = this.maxK_0;
    while (lo < hi) {
      var k = (lo + hi | 0) / 2 | 0;
      if (this.check_0(k)) {
        hi = k;
      }
       else {
        lo = k + 1 | 0;
      }
    }
    return hi;
  };
  GraphLevelSolver.prototype.check_0 = function (k) {
    var ranges = this.mkRanges_0(k);
    for (var i = 0; i !== ranges.length; ++i) {
      if (ranges[i].isEmpty) {
        return false;
      }
    }
    return true;
  };
  var Array_0 = Array;
  GraphLevelSolver.prototype.mkRanges_0 = function (k) {
    var array = Array_0(this.nodes_0.size);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = new GraphLevelSolver$Range(this.min_0, this.max_0);
    }
    var ranges = array;
    for (var i_0 = 0; i_0 !== ranges.length; ++i_0) {
      ranges[i_0].setMin_za3lpa$(this.nodes_0.get_za3lpa$(i_0).preferredPos - k | 0);
      ranges[i_0].setMax_za3lpa$(this.nodes_0.get_za3lpa$(i_0).preferredPos + k | 0);
    }
    for (var i_1 = 1; i_1 < ranges.length; i_1++) {
      ranges[i_1].setMin_za3lpa$(ranges[i_1 - 1 | 0].getMin() + this.nodes_0.get_za3lpa$(i_1 - 1 | 0).size | 0);
    }
    for (var i_2 = ranges.length - 2 | 0; i_2 >= 0; i_2--) {
      ranges[i_2].setMax_za3lpa$(ranges[i_2 + 1 | 0].getMax() - this.nodes_0.get_za3lpa$(i_2).size | 0);
    }
    return ranges;
  };
  function GraphLevelSolver$nodes$lambda(it) {
    return it.preferredPos;
  }
  GraphLevelSolver.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GraphLevelSolver',
    interfaces: []
  };
  function GraphNode() {
  }
  GraphNode.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'GraphNode',
    interfaces: []
  };
  function GraphSolver(graph) {
    this.graph_0 = null;
    this.graph_0 = new GraphWrapper(graph);
  }
  GraphSolver.prototype.solve = function () {
    var tmp$;
    var nodes = this.graph_0.nodes;
    var destination = ArrayList_init();
    var tmp$_0;
    tmp$_0 = nodes.iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      if (element.isReferenced)
        destination.add_11rb$(element);
    }
    var white = toMutableSet(destination);
    var levelIndex = 0;
    while (!white.isEmpty()) {
      var level = this.nextLevel_0(white);
      if (level.isEmpty()) {
        break;
      }
      white.removeAll_brywnq$(level);
      tmp$ = level.iterator();
      while (tmp$.hasNext()) {
        var graphNode = tmp$.next();
        graphNode.setLevel_za3lpa$(levelIndex);
      }
      levelIndex = levelIndex + 1 | 0;
      (new GraphLevelSolver(level)).solve();
    }
  };
  var Collection = Kotlin.kotlin.collections.Collection;
  GraphSolver.prototype.nextLevel_0 = function (white) {
    var destination = ArrayList_init();
    var tmp$;
    tmp$ = white.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var destination_0 = ArrayList_init();
      var tmp$_0;
      tmp$_0 = white.iterator();
      while (tmp$_0.hasNext()) {
        var element_0 = tmp$_0.next();
        if (element_0 !== element)
          destination_0.add_11rb$(element_0);
      }
      var none$result;
      none$break: do {
        var tmp$_1;
        if (Kotlin.isType(destination_0, Collection) && destination_0.isEmpty()) {
          none$result = true;
          break none$break;
        }
        tmp$_1 = destination_0.iterator();
        while (tmp$_1.hasNext()) {
          var element_1 = tmp$_1.next();
          if (this.hasPath_0(element_1, element)) {
            none$result = false;
            break none$break;
          }
        }
        none$result = true;
      }
       while (false);
      if (none$result)
        destination.add_11rb$(element);
    }
    return toList(destination);
  };
  GraphSolver.prototype.hasPath_0 = function (a, b) {
    var tmp$;
    var visited = HashSet_init();
    var queue = ArrayList_init();
    queue.add_11rb$(a);
    visited.add_11rb$(a);
    while (!queue.isEmpty()) {
      var cur = queue.removeAt_za3lpa$(0);
      var neighbours = this.graph_0.getNeighbours_dtm9gs$(cur);
      tmp$ = neighbours.iterator();
      while (tmp$.hasNext()) {
        var neighbour = tmp$.next();
        if (!visited.contains_11rb$(neighbour)) {
          if (equals(neighbour, b)) {
            return true;
          }
          queue.add_11rb$(neighbour);
          visited.add_11rb$(neighbour);
        }
      }
    }
    return false;
  };
  GraphSolver.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GraphSolver',
    interfaces: []
  };
  function GraphWrapper(graph) {
    this.nodeMap_0 = HashMap_init();
    this.edges_0 = HashMap_init();
    var tmp$;
    tmp$ = graph.nodes.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      this.depthFirst_0(graph, element, chainOf([]));
    }
  }
  Object.defineProperty(GraphWrapper.prototype, 'nodes', {
    get: function () {
      return this.nodeMap_0.values;
    }
  });
  function GraphWrapper$depthFirst$lambda(closure$next) {
    return function (it) {
      return equals(closure$next, it);
    };
  }
  GraphWrapper.prototype.depthFirst_0 = function (graph, node, chain) {
    var tmp$;
    if (this.nodeMap_0.containsKey_11rb$(node.id)) {
      return;
    }
    var id = node.id;
    this.nodeMap_0.put_xwzc9p$(id, node);
    var edgeSet = HashSet_init();
    tmp$ = graph.getNeighbours_dtm9gs$(node).iterator();
    while (tmp$.hasNext()) {
      var next = tmp$.next();
      if (chain.none_ucl7l2$(GraphWrapper$depthFirst$lambda(next))) {
        edgeSet.add_11rb$(next.id);
        this.depthFirst_0(graph, next, cons(next, chain));
      }
    }
    this.edges_0.put_xwzc9p$(id, edgeSet);
  };
  GraphWrapper.prototype.getNeighbours_dtm9gs$ = function (node) {
    var $receiver = ensureNotNull(this.edges_0.get_11rb$(node.id));
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(ensureNotNull(this.nodeMap_0.get_11rb$(item)));
    }
    return destination;
  };
  GraphWrapper.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GraphWrapper',
    interfaces: [Graph]
  };
  function Log(level) {
    Log$Companion_getInstance();
    this.indent_0 = null;
    var sb = new StringBuilder();
    for (var i = 0; i < level; i++) {
      sb.append_s8itvh$(32);
      sb.append_s8itvh$(32);
    }
    this.indent_0 = sb.toString();
  }
  Log.prototype.info_61zpoe$ = function (line) {
    println(this.indent_0 + line);
  };
  function Log$Companion() {
    Log$Companion_instance = this;
  }
  Log$Companion.prototype.getLog_za3lpa$ = function (level) {
    return new Log(level);
  };
  Log$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Log$Companion_instance = null;
  function Log$Companion_getInstance() {
    if (Log$Companion_instance === null) {
      new Log$Companion();
    }
    return Log$Companion_instance;
  }
  Log.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Log',
    interfaces: []
  };
  var ARROW_SIZE;
  function DynamicPoint2D() {
    this.x = null;
    this.y = null;
  }
  function DynamicPoint2D$interpolate$lambda(closure$t) {
    return function (s, e) {
      return s * (1 - closure$t) + e * closure$t;
    };
  }
  function DynamicPoint2D$interpolate$lambda_0(closure$t) {
    return function (s, e) {
      return s * (1 - closure$t) + e * closure$t;
    };
  }
  DynamicPoint2D.prototype.interpolate_e37ph5$ = function (endValue, t) {
    var x = this.x.combine_oj3ufw$(endValue.x, DynamicPoint2D$interpolate$lambda(t));
    var y = this.y.combine_oj3ufw$(endValue.y, DynamicPoint2D$interpolate$lambda_0(t));
    return DynamicPoint2D_init(x, y);
  };
  DynamicPoint2D.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DynamicPoint2D',
    interfaces: [Interpolatable]
  };
  function DynamicPoint2D_init(x, y, $this) {
    $this = $this || Object.create(DynamicPoint2D.prototype);
    DynamicPoint2D.call($this);
    $this.x = x;
    $this.y = y;
    return $this;
  }
  function DynamicPoint2D_init_0(x, y, $this) {
    $this = $this || Object.create(DynamicPoint2D.prototype);
    DynamicPoint2D.call($this);
    $this.x = new SimpleValue(x);
    $this.y = new SimpleValue(y);
    return $this;
  }
  function toTicks($receiver) {
    return Kotlin.Long.fromNumber($receiver * 6000);
  }
  function toSeconds($receiver) {
    return $receiver.toNumber() / 6000.0;
  }
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  var IllegalStateException_init = Kotlin.kotlin.IllegalStateException_init_pdl1vj$;
  function floorEntry($receiver, floor) {
    var tmp$;
    var result = LinkedHashMap_init();
    tmp$ = $receiver.entries.iterator();
    while (tmp$.hasNext()) {
      var entry = tmp$.next();
      if (Kotlin.compareTo(entry.key, floor) <= 0) {
        result.put_xwzc9p$(entry.key, entry.value);
      }
    }
    var $receiver_0 = result.entries;
    var maxBy$result;
    maxBy$break: do {
      var iterator = $receiver_0.iterator();
      if (!iterator.hasNext()) {
        maxBy$result = null;
        break maxBy$break;
      }
      var maxElem = iterator.next();
      var maxValue = maxElem.key;
      while (iterator.hasNext()) {
        var e = iterator.next();
        var v = e.key;
        if (Kotlin.compareTo(maxValue, v) < 0) {
          maxElem = e;
          maxValue = v;
        }
      }
      maxBy$result = maxElem;
    }
     while (false);
    var floorEntry = maxBy$result;
    var checkNotNull$result;
    if (floorEntry == null) {
      var message = 'No such element.';
      throw IllegalStateException_init(message.toString());
    }
     else {
      checkNotNull$result = floorEntry;
    }
    return checkNotNull$result;
  }
  function lastEntry($receiver) {
    var $receiver_0 = $receiver.entries;
    var maxBy$result;
    maxBy$break: do {
      var iterator = $receiver_0.iterator();
      if (!iterator.hasNext()) {
        maxBy$result = null;
        break maxBy$break;
      }
      var maxElem = iterator.next();
      var maxValue = maxElem.key;
      while (iterator.hasNext()) {
        var e = iterator.next();
        var v = e.key;
        if (Kotlin.compareTo(maxValue, v) < 0) {
          maxElem = e;
          maxValue = v;
        }
      }
      maxBy$result = maxElem;
    }
     while (false);
    var lastEntry = maxBy$result;
    var checkNotNull$result;
    if (lastEntry == null) {
      var message = 'No such element.';
      throw IllegalStateException_init(message.toString());
    }
     else {
      checkNotNull$result = lastEntry;
    }
    return checkNotNull$result;
  }
  function Signal(time, initValue) {
    this.keyPoints_0 = mutableMapOf([to(L0, new SignalConstant(initValue))]);
    this.value = time.map_4px9ds$(Signal$value$lambda(this));
  }
  Signal.prototype.lastValue = function () {
    var lastEntry_0 = lastEntry(this.keyPoints_0);
    var t = toSeconds(lastEntry_0.key);
    return lastEntry_0.value.apply_14dthe$(t);
  };
  Signal.prototype.apply_14dthe$ = function (t) {
    var ticks = toTicks(t);
    var floor = floorEntry(this.keyPoints_0, ticks);
    return floor.value.apply_14dthe$(t);
  };
  Signal.prototype.changeValue_sx9fpw$ = function (pathBuilder, newValue, ticksStart, ticksDuration) {
    var lastEntry_0 = lastEntry(this.keyPoints_0);
    var ticksLast = lastEntry_0.key;
    var oldValue = lastEntry_0.value.apply_14dthe$(0.0);
    if (ticksStart.compareTo_11rb$(ticksLast) < 0) {
      throw IllegalArgumentException_init(ticksStart.toString() + ' < ' + toString(ticksLast));
    }
    if (newValue !== oldValue) {
      var timeStart = toSeconds(ticksStart);
      var timeDuration = toSeconds(ticksDuration);
      var path = pathBuilder.start_trkh7z$(oldValue).end_trkh7z$(newValue).build();
      var $receiver = this.keyPoints_0;
      var value = new SignalTransform(timeStart, timeDuration, path);
      $receiver.put_xwzc9p$(ticksStart, value);
    }
    var ticksEnd = ticksStart.add(ticksDuration);
    var $receiver_0 = this.keyPoints_0;
    var value_0 = new SignalConstant(newValue);
    $receiver_0.put_xwzc9p$(ticksEnd, value_0);
  };
  Signal.prototype.getValue = function () {
    return this.value.value;
  };
  function Signal$value$lambda(this$Signal) {
    return function (t) {
      return this$Signal.apply_14dthe$(t);
    };
  }
  Signal.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Signal',
    interfaces: []
  };
  function SignalConstant(value) {
    SignalFunction.call(this);
    this.value_0 = value;
  }
  SignalConstant.prototype.apply_14dthe$ = function (t) {
    return this.value_0;
  };
  SignalConstant.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignalConstant',
    interfaces: [SignalFunction]
  };
  function SignalCurve(start, end, control1, control2) {
    SignalPath.call(this, start, end);
    this.control1_0 = control1;
    this.control2_0 = control2;
  }
  function SignalCurve$Builder() {
    SignalPath$Builder.call(this);
  }
  function SignalCurve$Builder$build$lambda(start, end) {
    return 3.0 / 2.0 * distance(start, end);
  }
  function SignalCurve$Builder$build$lambda_0(x, v) {
    return x + v / 3.0;
  }
  function SignalCurve$Builder$build$lambda_1(x, v) {
    return x + v / 3.0;
  }
  SignalCurve$Builder.prototype.build = function () {
    var pointStart = this.start.x.combine_oj3ufw$(this.start.y, getCallableRef('Pair', function (first, second) {
      return new Pair(first, second);
    }));
    var pointEnd = this.end.x.combine_oj3ufw$(this.end.y, getCallableRef('Pair', function (first, second) {
      return new Pair(first, second);
    }));
    var valueV = pointStart.combine_oj3ufw$(pointEnd, SignalCurve$Builder$build$lambda);
    var control1X = this.start.x.combine_oj3ufw$(valueV, SignalCurve$Builder$build$lambda_0);
    var control1 = DynamicPoint2D_init(control1X, this.start.y);
    var control2X = this.end.x.combine_oj3ufw$(valueV, SignalCurve$Builder$build$lambda_1);
    var control2 = DynamicPoint2D_init(control2X, this.end.y);
    return new SignalCurve(this.start, this.end, control1, control2);
  };
  SignalCurve$Builder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Builder',
    interfaces: [SignalPath$Builder]
  };
  SignalCurve.prototype.apply_14dthe$ = function (t) {
    var x = BezierBinding(this.start.x, this.control1_0.x, this.control2_0.x, this.end.x, t);
    var y = BezierBinding(this.start.y, this.control1_0.y, this.control2_0.y, this.end.y, t);
    return DynamicPoint2D_init(x, y);
  };
  SignalCurve.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignalCurve',
    interfaces: [SignalPath]
  };
  function BezierBinding$lambda(closure$t) {
    return function (g0g1, g2g3) {
      return bezier(g0g1.first, g0g1.second, g2g3.first, g2g3.second, closure$t);
    };
  }
  function BezierBinding(g0, g1, g2, g3, t) {
    return g0.combine_oj3ufw$(g1, getCallableRef('Pair', function (first, second) {
      return new Pair(first, second);
    })).combine_oj3ufw$(g2.combine_oj3ufw$(g3, getCallableRef('Pair', function (first, second) {
      return new Pair(first, second);
    })), BezierBinding$lambda(t));
  }
  var Math_0 = Math;
  function distance($receiver, other) {
    var dx = $receiver.first - other.first;
    var dy = $receiver.second - other.second;
    var x = dx * dx + dy * dy;
    return Math_0.sqrt(x);
  }
  function bezier(g0, g1, g2, g3, t) {
    var a3 = -g0 + 3 * g1 - 3 * g2 + g3;
    var a2 = 3 * g0 - 6 * g1 + 3 * g2;
    var a1 = -3 * g0 + 3 * g1;
    return a3 * t * t * t + a2 * t * t + a1 * t + g0;
  }
  function SignalDoubleLine(start, end) {
    SignalPath.call(this, start, end);
  }
  function SignalDoubleLine$Builder() {
    SignalPath$Builder.call(this);
  }
  SignalDoubleLine$Builder.prototype.build = function () {
    return new SignalDoubleLine(this.start, this.end);
  };
  SignalDoubleLine$Builder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Builder',
    interfaces: [SignalPath$Builder]
  };
  SignalDoubleLine.prototype.apply_14dthe$ = function (t) {
    return this.start * (1.0 - t) + this.end * t;
  };
  SignalDoubleLine.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignalDoubleLine',
    interfaces: [SignalPath]
  };
  function SignalEvent(signal, value, pathBuilder) {
    this.signal = signal;
    this.value = value;
    this.pathBuilder = pathBuilder;
  }
  SignalEvent.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignalEvent',
    interfaces: []
  };
  function SignalFunction() {
  }
  SignalFunction.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignalFunction',
    interfaces: []
  };
  function SignalInterpolatable(start, end) {
    SignalPath.call(this, start, end);
  }
  function SignalInterpolatable$Builder() {
    SignalPath$Builder.call(this);
  }
  SignalInterpolatable$Builder.prototype.build = function () {
    return new SignalInterpolatable(this.start, this.end);
  };
  SignalInterpolatable$Builder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Builder',
    interfaces: [SignalPath$Builder]
  };
  SignalInterpolatable.prototype.apply_14dthe$ = function (t) {
    return this.start.interpolate_e37ph5$(this.end, t);
  };
  SignalInterpolatable.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignalInterpolatable',
    interfaces: [SignalPath]
  };
  function SignalJump(start, end, jumpTime) {
    SignalPath.call(this, start, end);
    this.jumpTime_0 = jumpTime;
  }
  function SignalJump$Builder(jumpTime) {
    if (jumpTime === void 0)
      jumpTime = 0.5;
    SignalPath$Builder.call(this);
    this.jumpTime_0 = jumpTime;
  }
  SignalJump$Builder.prototype.build = function () {
    return new SignalJump(this.start, this.end, this.jumpTime_0);
  };
  SignalJump$Builder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Builder',
    interfaces: [SignalPath$Builder]
  };
  SignalJump.prototype.apply_14dthe$ = function (t) {
    return t < this.jumpTime_0 ? this.start : this.end;
  };
  SignalJump.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignalJump',
    interfaces: [SignalPath]
  };
  function SignalPath(start, end) {
    this.start = start;
    this.end = end;
  }
  function SignalPath$Builder() {
    this.start_71dkg2$_0 = this.start_71dkg2$_0;
    this.end_tposfd$_0 = this.end_tposfd$_0;
  }
  Object.defineProperty(SignalPath$Builder.prototype, 'start', {
    get: function () {
      if (this.start_71dkg2$_0 == null)
        return throwUPAE('start');
      return this.start_71dkg2$_0;
    },
    set: function (start) {
      this.start_71dkg2$_0 = start;
    }
  });
  Object.defineProperty(SignalPath$Builder.prototype, 'end', {
    get: function () {
      if (this.end_tposfd$_0 == null)
        return throwUPAE('end');
      return this.end_tposfd$_0;
    },
    set: function (end) {
      this.end_tposfd$_0 = end;
    }
  });
  SignalPath$Builder.prototype.start_trkh7z$ = function (start) {
    this.start = start;
    return this;
  };
  SignalPath$Builder.prototype.end_trkh7z$ = function (end) {
    this.end = end;
    return this;
  };
  SignalPath$Builder.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Builder',
    interfaces: []
  };
  SignalPath.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignalPath',
    interfaces: []
  };
  function SignalTransform(timeStart, timeDuration, path) {
    SignalFunction.call(this);
    this.timeStart_0 = timeStart;
    this.timeDuration_0 = timeDuration;
    this.path_0 = path;
  }
  SignalTransform.prototype.apply_14dthe$ = function (t) {
    return this.path_0.apply_14dthe$((t - this.timeStart_0) / this.timeDuration_0);
  };
  SignalTransform.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SignalTransform',
    interfaces: [SignalFunction]
  };
  function Bounds(minX, minY, width, height) {
    Bounds$Companion_getInstance();
    this.minX = minX;
    this.minY = minY;
    this.width = width;
    this.height = height;
    this.maxX_0 = this.minX + this.width;
    this.maxY_0 = this.minY + this.height;
  }
  Bounds.prototype.union_v7rwv9$ = function (other) {
    var tmp$ = Bounds$Companion_getInstance();
    var a = this.minX;
    var b = other.minX;
    var tmp$_0 = Math_0.min(a, b);
    var a_0 = this.minY;
    var b_0 = other.minY;
    var tmp$_1 = Math_0.min(a_0, b_0);
    var a_1 = this.maxX_0;
    var b_1 = other.maxX_0;
    var tmp$_2 = Math_0.max(a_1, b_1);
    var a_2 = this.maxY_0;
    var b_2 = other.maxY_0;
    return tmp$.fromMinMax_6y0v78$(tmp$_0, tmp$_1, tmp$_2, Math_0.max(a_2, b_2));
  };
  function Bounds$Companion() {
    Bounds$Companion_instance = this;
  }
  Bounds$Companion.prototype.fromPoint_lu1900$ = function (x, y) {
    return new Bounds(x, y, x, y);
  };
  Bounds$Companion.prototype.fromMinMax_6y0v78$ = function (minX, minY, maxX, maxY) {
    return new Bounds(minX, minY, maxX - minX, maxY - minY);
  };
  Bounds$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Bounds$Companion_instance = null;
  function Bounds$Companion_getInstance() {
    if (Bounds$Companion_instance === null) {
      new Bounds$Companion();
    }
    return Bounds$Companion_instance;
  }
  Bounds.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Bounds',
    interfaces: []
  };
  Bounds.prototype.component1 = function () {
    return this.minX;
  };
  Bounds.prototype.component2 = function () {
    return this.minY;
  };
  Bounds.prototype.component3 = function () {
    return this.width;
  };
  Bounds.prototype.component4 = function () {
    return this.height;
  };
  Bounds.prototype.copy_6y0v78$ = function (minX, minY, width, height) {
    return new Bounds(minX === void 0 ? this.minX : minX, minY === void 0 ? this.minY : minY, width === void 0 ? this.width : width, height === void 0 ? this.height : height);
  };
  Bounds.prototype.toString = function () {
    return 'Bounds(minX=' + Kotlin.toString(this.minX) + (', minY=' + Kotlin.toString(this.minY)) + (', width=' + Kotlin.toString(this.width)) + (', height=' + Kotlin.toString(this.height)) + ')';
  };
  Bounds.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.minX) | 0;
    result = result * 31 + Kotlin.hashCode(this.minY) | 0;
    result = result * 31 + Kotlin.hashCode(this.width) | 0;
    result = result * 31 + Kotlin.hashCode(this.height) | 0;
    return result;
  };
  Bounds.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.minX, other.minX) && Kotlin.equals(this.minY, other.minY) && Kotlin.equals(this.width, other.width) && Kotlin.equals(this.height, other.height)))));
  };
  function Color(red, green, blue) {
    Color$Companion_getInstance();
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  Color.prototype.interpolate_e37ph5$ = function (endValue, t) {
    if (t <= 0.0)
      return this;
    if (t >= 1.0)
      return endValue;
    var ft = t;
    return new Color(this.red + (endValue.red - this.red) * ft, this.green + (endValue.green - this.green) * ft, this.blue + (endValue.blue - this.blue) * ft);
  };
  Color.prototype.toString = function () {
    return 'rgb(' + byte(this.red) + ',' + byte(this.green) + ',' + byte(this.blue) + ')';
  };
  function Color$Companion() {
    Color$Companion_instance = this;
    this.BLACK = new Color(0.0, 0.0, 0.0);
    this.BLUE = new Color(0.0, 0.0, 1.0);
    this.RED = new Color(1.0, 0.0, 0.0);
    this.GREEN = new Color(0.0, 0.5019608, 0.0);
    this.WHITE = new Color(1.0, 1.0, 1.0);
  }
  Color$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Color$Companion_instance = null;
  function Color$Companion_getInstance() {
    if (Color$Companion_instance === null) {
      new Color$Companion();
    }
    return Color$Companion_instance;
  }
  Color.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Color',
    interfaces: [Interpolatable]
  };
  Color.prototype.component1 = function () {
    return this.red;
  };
  Color.prototype.component2 = function () {
    return this.green;
  };
  Color.prototype.component3 = function () {
    return this.blue;
  };
  Color.prototype.copy_yvo9jy$ = function (red, green, blue) {
    return new Color(red === void 0 ? this.red : red, green === void 0 ? this.green : green, blue === void 0 ? this.blue : blue);
  };
  Color.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.red) | 0;
    result = result * 31 + Kotlin.hashCode(this.green) | 0;
    result = result * 31 + Kotlin.hashCode(this.blue) | 0;
    return result;
  };
  Color.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.red, other.red) && Kotlin.equals(this.green, other.green) && Kotlin.equals(this.blue, other.blue)))));
  };
  function byte(value) {
    return numberToInt(value * 255);
  }
  function Interpolatable() {
  }
  Interpolatable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Interpolatable',
    interfaces: []
  };
  function Value(v) {
    this.v_r30w56$_0 = v;
    this.observers_u76f1v$_0 = ArrayList_init();
  }
  Object.defineProperty(Value.prototype, 'value', {
    get: function () {
      return this.v_r30w56$_0;
    },
    set: function (newValue) {
      this.v_r30w56$_0 = newValue;
      var tmp$;
      tmp$ = this.observers_u76f1v$_0.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        element(newValue);
      }
    }
  });
  Value.prototype.addObserver_oh3mgy$ = function (observer) {
    return this.observers_u76f1v$_0.add_11rb$(observer);
  };
  Value.prototype.map_4px9ds$ = function (mapper) {
    return new MappedValue(this, mapper);
  };
  Value.prototype.combine_oj3ufw$ = function (other, combiner) {
    return new CombinedValue(this, other, combiner);
  };
  Value.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Value',
    interfaces: []
  };
  function SimpleValue(initValue) {
    Value.call(this, initValue);
  }
  Object.defineProperty(SimpleValue.prototype, 'value', {
    get: function () {
      return Kotlin.callGetter(this, Value.prototype, 'value');
    },
    set: function (newValue) {
      Kotlin.callSetter(this, Value.prototype, 'value', newValue);
    }
  });
  SimpleValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SimpleValue',
    interfaces: [Value]
  };
  function MappedValue(observable, mapper) {
    Value.call(this, mapper(observable.value));
    this.observable_0 = observable;
    this.mapper_0 = mapper;
    this.observable_0.addObserver_oh3mgy$(MappedValue_init$lambda(this));
  }
  function MappedValue_init$lambda(this$MappedValue) {
    return function (newValue) {
      this$MappedValue.value = this$MappedValue.mapper_0(newValue);
      return Unit;
    };
  }
  MappedValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MappedValue',
    interfaces: [Value]
  };
  function CombinedValue(observable1, observable2, combiner) {
    Value.call(this, combiner(observable1.value, observable2.value));
    this.observable1_0 = observable1;
    this.observable2_0 = observable2;
    this.combiner_0 = combiner;
    this.observable1_0.addObserver_oh3mgy$(CombinedValue_init$lambda(this));
    this.observable2_0.addObserver_oh3mgy$(CombinedValue_init$lambda_0(this));
  }
  CombinedValue.prototype.update_0 = function () {
    this.value = this.combiner_0(this.observable1_0.value, this.observable2_0.value);
  };
  function CombinedValue_init$lambda(this$CombinedValue) {
    return function (f) {
      this$CombinedValue.update_0();
      return Unit;
    };
  }
  function CombinedValue_init$lambda_0(this$CombinedValue) {
    return function (f) {
      this$CombinedValue.update_0();
      return Unit;
    };
  }
  CombinedValue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CombinedValue',
    interfaces: [Value]
  };
  function Observer() {
  }
  Observer.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Observer',
    interfaces: []
  };
  function emptyChain() {
    return new EmptyChain();
  }
  var get_lastIndex = Kotlin.kotlin.collections.get_lastIndex_m7z4lg$;
  function chainOf(data) {
    var initial = new EmptyChain();
    var tmp$;
    var index = get_lastIndex(data);
    var accumulator = initial;
    while (index >= 0) {
      accumulator = new EagerChain(data[tmp$ = index, index = tmp$ - 1 | 0, tmp$], accumulator);
    }
    return accumulator;
  }
  function cons(head, tail) {
    return new EagerChain(head, tail);
  }
  function Chain() {
  }
  Object.defineProperty(Chain.prototype, 'empty', {
    get: function () {
      return false;
    }
  });
  Chain.prototype.concat_qwcz9q$ = function (other) {
    return new ConcatChain(this, other);
  };
  Chain.prototype.none_ucl7l2$ = function (predicate) {
    return !this.any_ucl7l2$(predicate);
  };
  Chain.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Chain',
    interfaces: []
  };
  function EmptyChain() {
    Chain.call(this);
  }
  Object.defineProperty(EmptyChain.prototype, 'head', {
    get: function () {
      throw NoSuchElementException_init();
    }
  });
  Object.defineProperty(EmptyChain.prototype, 'tail', {
    get: function () {
      throw NoSuchElementException_init();
    }
  });
  Object.defineProperty(EmptyChain.prototype, 'length', {
    get: function () {
      return 0;
    }
  });
  Object.defineProperty(EmptyChain.prototype, 'empty', {
    get: function () {
      return true;
    }
  });
  EmptyChain.prototype.concat_qwcz9q$ = function (other) {
    return other;
  };
  EmptyChain.prototype.any_ucl7l2$ = function (predicate) {
    return false;
  };
  EmptyChain.prototype.foldl_b8xf17$ = function (init, op) {
    return init;
  };
  EmptyChain.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EmptyChain',
    interfaces: [Chain]
  };
  function EagerChain(head, tail) {
    Chain.call(this);
    this.head_ift81b$_0 = head;
    this.tail_i9a2qn$_0 = tail;
    this.length_pirs3v$_0 = 1 + this.tail.length | 0;
  }
  Object.defineProperty(EagerChain.prototype, 'head', {
    get: function () {
      return this.head_ift81b$_0;
    }
  });
  Object.defineProperty(EagerChain.prototype, 'tail', {
    get: function () {
      return this.tail_i9a2qn$_0;
    }
  });
  Object.defineProperty(EagerChain.prototype, 'length', {
    get: function () {
      return this.length_pirs3v$_0;
    }
  });
  EagerChain.prototype.any_ucl7l2$ = function (predicate) {
    return predicate(this.head) || this.tail.any_ucl7l2$(predicate);
  };
  EagerChain.prototype.foldl_b8xf17$ = function (init, op) {
    return this.tail.foldl_b8xf17$(op(init, this.head), op);
  };
  EagerChain.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EagerChain',
    interfaces: [Chain]
  };
  function ConcatChain(left, right) {
    Chain.call(this);
    this.left_0 = left;
    this.right_0 = right;
    this.length_searbd$_0 = this.left_0.length + this.right_0.length | 0;
  }
  Object.defineProperty(ConcatChain.prototype, 'head', {
    get: function () {
      return this.left_0.head;
    }
  });
  Object.defineProperty(ConcatChain.prototype, 'tail', {
    get: function () {
      return this.left_0.tail.concat_qwcz9q$(this.right_0);
    }
  });
  Object.defineProperty(ConcatChain.prototype, 'length', {
    get: function () {
      return this.length_searbd$_0;
    }
  });
  ConcatChain.prototype.any_ucl7l2$ = function (predicate) {
    return this.left_0.any_ucl7l2$(predicate) || this.right_0.any_ucl7l2$(predicate);
  };
  ConcatChain.prototype.foldl_b8xf17$ = function (init, op) {
    return this.right_0.foldl_b8xf17$(this.left_0.foldl_b8xf17$(init, op), op);
  };
  ConcatChain.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ConcatChain',
    interfaces: [Chain]
  };
  function newSVG(qualifiedName) {
    return document.createElementNS('http://www.w3.org/2000/svg', qualifiedName);
  }
  function newSVGSVG() {
    var tmp$;
    return Kotlin.isType(tmp$ = newSVG('svg'), SVGSVGElement) ? tmp$ : throwCCE();
  }
  function newSVGSVG_0(width, height) {
    var $receiver = newSVGSVG();
    $receiver.width.baseVal.valueAsString = width.toString();
    $receiver.height.baseVal.valueAsString = height.toString();
    return $receiver;
  }
  function newSVGRect() {
    var tmp$;
    return Kotlin.isType(tmp$ = newSVG('rect'), SVGRectElement) ? tmp$ : throwCCE();
  }
  function newSVGPath() {
    var tmp$;
    return Kotlin.isType(tmp$ = newSVG('path'), SVGPathElement) ? tmp$ : throwCCE();
  }
  function newSVGRect_0(x, y, width, height, fill) {
    var $receiver = newSVGRect();
    $receiver.x.baseVal.valueAsString = x.toString();
    $receiver.y.baseVal.valueAsString = y.toString();
    $receiver.width.baseVal.valueAsString = width.toString();
    $receiver.height.baseVal.valueAsString = height.toString();
    $receiver.setAttribute('fill', fill.toString());
    return $receiver;
  }
  function newSVGGroup() {
    var tmp$;
    return Kotlin.isType(tmp$ = newSVG('g'), SVGGElement) ? tmp$ : throwCCE();
  }
  function newSVGText() {
    var tmp$;
    return Kotlin.isType(tmp$ = newSVG('text'), SVGTextElement) ? tmp$ : throwCCE();
  }
  function JsNodeCreator() {
  }
  JsNodeCreator.prototype.visitPath_eorgsy$ = function (elementPath) {
    var $receiver = newSVGPath();
    var path = this.mkDynamicCurveEastEast_0(elementPath.origin, elementPath.dest);
    var withArrow = this.addDynamicArrow_0(path, elementPath.dest);
    bindAttribute($receiver, 'd', withArrow);
    $receiver.setAttribute('fill', 'transparent');
    $receiver.setAttribute('stroke', 'black');
    $receiver.setAttribute('stroke-width', '2');
    return $receiver;
  };
  JsNodeCreator.prototype.visitRectangle_f3mfoi$ = function (elementRectangle) {
    var $receiver = newSVGRect();
    bindLength($receiver.x, elementRectangle.x);
    bindLength($receiver.y, elementRectangle.y);
    bindLength($receiver.width, elementRectangle.width);
    bindLength($receiver.height, elementRectangle.height);
    bindAttribute($receiver, 'fill', elementRectangle.fill.value);
    bindAttribute($receiver, 'fill-opacity', elementRectangle.opacity.value);
    return $receiver;
  };
  function JsNodeCreator$visitObject$lambda$lambda(it) {
    return 'translate(' + it.x.value + ', ' + it.y.value + ')';
  }
  JsNodeCreator.prototype.visitObject_kkvjv8$ = function (elementObject) {
    var $receiver = newSVGGroup();
    var width = 60;
    var height = 40;
    if (elementObject.isUseRect) {
      var rectOuter = newSVGRect_0(-width / 2, -height / 2, width, height, Color$Companion_getInstance().BLACK);
      var rectInner = newSVGRect_0(-width / 2 + 1, -height / 2 + 1, width - 2, height - 2, Color$Companion_getInstance().WHITE);
      $receiver.appendChild(rectOuter);
      $receiver.appendChild(rectInner);
    }
    var headLine = elementObject.headLine;
    var text = newSVGText();
    text.textContent = headLine;
    text.setAttribute('text-anchor', 'middle');
    text.setAttribute('alignment-baseline', 'middle');
    $receiver.appendChild(text);
    var translate = elementObject.translate.value.map_4px9ds$(JsNodeCreator$visitObject$lambda$lambda);
    bindAttribute($receiver, 'transform', translate);
    return $receiver;
  };
  function JsNodeCreator$mkDynamicCurveEastEast$lambda(pOrigin, pDest) {
    var dx = pDest.first - pOrigin.first;
    var dy = pDest.second - pOrigin.second;
    var x = 4.0 * dx * dx + 3.0 * dy * dy;
    return Math_0.sqrt(x) - dx;
  }
  function JsNodeCreator$mkDynamicCurveEastEast$lambda_0(it) {
    return it / 3.0;
  }
  function JsNodeCreator$mkDynamicCurveEastEast$lambda_1(x, v) {
    return x + v;
  }
  function JsNodeCreator$mkDynamicCurveEastEast$lambda_2(x, v) {
    return x - v;
  }
  JsNodeCreator.prototype.mkDynamicCurveEastEast_0 = function (origin, dest) {
    var vOrigin = origin.x.combine_oj3ufw$(origin.y, getCallableRef('Pair', function (first, second) {
      return new Pair(first, second);
    }));
    var vDest = dest.x.combine_oj3ufw$(dest.y, getCallableRef('Pair', function (first, second) {
      return new Pair(first, second);
    }));
    var vValue = vOrigin.combine_oj3ufw$(vDest, JsNodeCreator$mkDynamicCurveEastEast$lambda);
    var vValue3 = vValue.map_4px9ds$(JsNodeCreator$mkDynamicCurveEastEast$lambda_0);
    var controlX1 = origin.x.combine_oj3ufw$(vValue3, JsNodeCreator$mkDynamicCurveEastEast$lambda_1);
    var controlY1 = origin.y;
    var controlX2 = dest.x.combine_oj3ufw$(vValue3, JsNodeCreator$mkDynamicCurveEastEast$lambda_2);
    var controlY2 = dest.y;
    return this.mkDynamicCurve_0(origin, dest, DynamicPoint2D_init(controlX1, controlY1), DynamicPoint2D_init(controlX2, controlY2));
  };
  function JsNodeCreator$mkDynamicCurve$lambda(x, y) {
    return x.toString() + ' ' + y;
  }
  function JsNodeCreator$mkDynamicCurve$lambda_0(x, y) {
    return x.toString() + ' ' + y;
  }
  function JsNodeCreator$mkDynamicCurve$lambda_1(x, y) {
    return x.toString() + ' ' + y;
  }
  function JsNodeCreator$mkDynamicCurve$lambda_2(x, y) {
    return x.toString() + ' ' + y;
  }
  function JsNodeCreator$mkDynamicCurve$lambda_3(c1, c2) {
    return c1 + ', ' + c2;
  }
  function JsNodeCreator$mkDynamicCurve$lambda_4(c, d) {
    return c + ', ' + d;
  }
  function JsNodeCreator$mkDynamicCurve$lambda_5(p, c) {
    return 'M ' + p + ' C ' + c;
  }
  JsNodeCreator.prototype.mkDynamicCurve_0 = function (origin, dest, control1, control2) {
    var p0 = origin.x.combine_oj3ufw$(origin.y, JsNodeCreator$mkDynamicCurve$lambda);
    var p1 = control1.x.combine_oj3ufw$(control1.y, JsNodeCreator$mkDynamicCurve$lambda_0);
    var p2 = control2.x.combine_oj3ufw$(control2.y, JsNodeCreator$mkDynamicCurve$lambda_1);
    var p3 = dest.x.combine_oj3ufw$(dest.y, JsNodeCreator$mkDynamicCurve$lambda_2);
    var control = p1.combine_oj3ufw$(p2, JsNodeCreator$mkDynamicCurve$lambda_3);
    var curve = control.combine_oj3ufw$(p3, JsNodeCreator$mkDynamicCurve$lambda_4);
    return p0.combine_oj3ufw$(curve, JsNodeCreator$mkDynamicCurve$lambda_5);
  };
  function JsNodeCreator$addDynamicArrow$lambda(y) {
    return y - 5;
  }
  function JsNodeCreator$addDynamicArrow$lambda_0(x, y) {
    return 'M ' + x + ' ' + y;
  }
  function JsNodeCreator$addDynamicArrow$lambda_1(y) {
    return y + 5;
  }
  function JsNodeCreator$addDynamicArrow$lambda_2(x, y) {
    return 'L ' + x + ' ' + y;
  }
  function JsNodeCreator$addDynamicArrow$lambda_3(y) {
    return y + 5;
  }
  function JsNodeCreator$addDynamicArrow$lambda_4(x, y) {
    return 'L ' + x + ' ' + y;
  }
  function JsNodeCreator$addDynamicArrow$lambda_5(u, v) {
    return u + ' ' + v;
  }
  function JsNodeCreator$addDynamicArrow$lambda_6(u, v) {
    return u + ' ' + v;
  }
  function JsNodeCreator$addDynamicArrow$lambda_7(u, v) {
    return u + ' ' + v;
  }
  JsNodeCreator.prototype.addDynamicArrow_0 = function (path, end) {
    var x1 = end.x;
    var y1 = end.y.map_4px9ds$(JsNodeCreator$addDynamicArrow$lambda);
    var e1 = x1.combine_oj3ufw$(y1, JsNodeCreator$addDynamicArrow$lambda_0);
    var x2 = end.x.map_4px9ds$(JsNodeCreator$addDynamicArrow$lambda_1);
    var y2 = end.y;
    var e2 = x2.combine_oj3ufw$(y2, JsNodeCreator$addDynamicArrow$lambda_2);
    var x3 = end.x;
    var y3 = end.y.map_4px9ds$(JsNodeCreator$addDynamicArrow$lambda_3);
    var e3 = x3.combine_oj3ufw$(y3, JsNodeCreator$addDynamicArrow$lambda_4);
    return path.combine_oj3ufw$(e1.combine_oj3ufw$(e2, JsNodeCreator$addDynamicArrow$lambda_5).combine_oj3ufw$(e3, JsNodeCreator$addDynamicArrow$lambda_6), JsNodeCreator$addDynamicArrow$lambda_7);
  };
  JsNodeCreator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'JsNodeCreator',
    interfaces: [ElementNodeVisitor]
  };
  function bindAttribute$lambda(closure$name, this$bindAttribute) {
    return function (newValue) {
      this$bindAttribute.setAttribute(closure$name, newValue.toString());
      return Unit;
    };
  }
  function bindAttribute($receiver, name, value) {
    $receiver.setAttribute(name, value.value.toString());
    value.addObserver_oh3mgy$(bindAttribute$lambda(name, $receiver));
  }
  function bindLength$lambda(closure$length) {
    return function (newValue) {
      closure$length.baseVal.valueAsString = newValue.toString();
      return Unit;
    };
  }
  function bindLength(length, signal) {
    length.baseVal.valueAsString = signal.getValue().toString();
    signal.value.addObserver_oh3mgy$(bindLength$lambda(length));
  }
  function PathMaker() {
    PathMaker_instance = this;
  }
  PathMaker.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'PathMaker',
    interfaces: []
  };
  var PathMaker_instance = null;
  function PathMaker_getInstance() {
    if (PathMaker_instance === null) {
      new PathMaker();
    }
    return PathMaker_instance;
  }
  var package$de = _.de || (_.de = {});
  var package$fluxparticle = package$de.fluxparticle || (package$de.fluxparticle = {});
  var package$animation = package$fluxparticle.animation || (package$fluxparticle.animation = {});
  package$animation.AnimationCollection = AnimationCollection;
  package$animation.fadeIn_eoqgtr$ = fadeIn;
  package$animation.show_eoqgtr$ = show;
  package$animation.fadeOut_eoqgtr$ = fadeOut;
  package$animation.hide_eoqgtr$ = hide;
  package$animation.move_w1vyl2$ = move;
  package$animation.adjustAreaToBounds_62vizf$ = adjustAreaToBounds;
  package$animation.changeColor_g90n0d$ = changeColor;
  package$animation.update_4acdoi$ = update;
  Object.defineProperty(AnimationQueue, 'Companion', {
    get: AnimationQueue$Companion_getInstance
  });
  package$animation.AnimationQueue = AnimationQueue;
  package$animation.Box = Box;
  package$animation.Clip = Clip;
  package$animation.Timeframe = Timeframe;
  var package$elementobject = package$animation.elementobject || (package$animation.elementobject = {});
  package$elementobject.ElementNode = ElementNode;
  package$elementobject.ElementNodeVisitor = ElementNodeVisitor;
  Object.defineProperty(ElementObject, 'Companion', {
    get: ElementObject$Companion_getInstance
  });
  package$elementobject.ElementObject = ElementObject;
  package$elementobject.ElementPath = ElementPath;
  package$elementobject.ElementRectangle = ElementRectangle;
  var package$graph = package$animation.graph || (package$animation.graph = {});
  package$graph.Graph = Graph;
  package$graph.GraphLevelSolver = GraphLevelSolver;
  package$graph.GraphNode = GraphNode;
  package$graph.GraphSolver = GraphSolver;
  package$graph.GraphWrapper = GraphWrapper;
  Object.defineProperty(Log, 'Companion', {
    get: Log$Companion_getInstance
  });
  var package$output = package$animation.output || (package$animation.output = {});
  package$output.Log = Log;
  var package$path = package$animation.path || (package$animation.path = {});
  Object.defineProperty(package$path, 'ARROW_SIZE', {
    get: function () {
      return ARROW_SIZE;
    }
  });
  var package$point = package$animation.point || (package$animation.point = {});
  package$point.DynamicPoint2D_init_ceoe38$ = DynamicPoint2D_init;
  package$point.DynamicPoint2D_init_lu1900$ = DynamicPoint2D_init_0;
  package$point.DynamicPoint2D = DynamicPoint2D;
  var package$signal = package$animation.signal || (package$animation.signal = {});
  package$signal.toTicks_yrwdxr$ = toTicks;
  package$signal.toSeconds_mts6qi$ = toSeconds;
  package$signal.floorEntry_fcfgoh$ = floorEntry;
  package$signal.lastEntry_g7sijz$ = lastEntry;
  package$signal.Signal = Signal;
  package$signal.SignalConstant = SignalConstant;
  SignalCurve.Builder = SignalCurve$Builder;
  package$signal.SignalCurve = SignalCurve;
  SignalDoubleLine.Builder = SignalDoubleLine$Builder;
  package$signal.SignalDoubleLine = SignalDoubleLine;
  package$signal.SignalEvent = SignalEvent;
  package$signal.SignalFunction = SignalFunction;
  SignalInterpolatable.Builder = SignalInterpolatable$Builder;
  package$signal.SignalInterpolatable = SignalInterpolatable;
  SignalJump.Builder = SignalJump$Builder;
  package$signal.SignalJump = SignalJump;
  SignalPath.Builder = SignalPath$Builder;
  package$signal.SignalPath = SignalPath;
  package$signal.SignalTransform = SignalTransform;
  Object.defineProperty(Bounds, 'Companion', {
    get: Bounds$Companion_getInstance
  });
  var package$util = package$animation.util || (package$animation.util = {});
  package$util.Bounds = Bounds;
  Object.defineProperty(Color, 'Companion', {
    get: Color$Companion_getInstance
  });
  package$util.Color = Color;
  package$util.Interpolatable = Interpolatable;
  var package$value = package$animation.value || (package$animation.value = {});
  package$value.Value = Value;
  package$value.SimpleValue = SimpleValue;
  package$value.Observer = Observer;
  var package$utils = package$fluxparticle.utils || (package$fluxparticle.utils = {});
  var package$chain = package$utils.chain || (package$utils.chain = {});
  package$chain.emptyChain_287e2$ = emptyChain;
  package$chain.chainOf_i5x0yv$ = chainOf;
  package$chain.cons_i1115r$ = cons;
  package$chain.Chain = Chain;
  package$animation.newSVGSVG_dleff0$ = newSVGSVG_0;
  package$animation.JsNodeCreator = JsNodeCreator;
  Object.defineProperty(package$path, 'PathMaker', {
    get: PathMaker_getInstance
  });
  ARROW_SIZE = 5;
  Kotlin.defineModule('js', _);
  return _;
}(typeof js === 'undefined' ? {} : js, kotlin);
