(ns ui.view.photo
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]
            [cljsjs.photoswipe]
            [cljsjs.photoswipe-ui-default]))

(defui PhotoView
  Object
  (componentDidMount [this]
                     (js/eval
                       "var initPhotoSwipeFromDOM = function( gallerySelector ) {
                       var parseThumbnailElements = function(el) {
                       var thumbElements = el.childNodes,
                       numNodes = thumbElements.length,
                       items = [],
                       figureEl,
                       linkEl,
                       size,
                       item;

                       for(var i = 0; i < numNodes; i++) {

                       figureEl = thumbElements[i];

                       if(figureEl.nodeType !== 1) {
                       continue;
                       }

                       linkEl = figureEl.children[0];

                       size = linkEl.getAttribute('data-size').split('x');
                       item = {
                       src: linkEl.getAttribute('href'),
                       w: parseInt(size[0], 10),
                       h: parseInt(size[1], 10)
                       };

                       if(figureEl.children.length > 1) {

                       item.title = figureEl.children[1].innerHTML;
                       }

                       if(linkEl.children.length > 0) {

                       item.msrc = linkEl.children[0].getAttribute('src');
                       }

                       item.el = figureEl;
                       items.push(item);
                       }

                       return items;
                       };
                       var closest = function closest(el, fn) {
                       return el && ( fn(el) ? el : closest(el.parentNode, fn) );
                       };
                       var onThumbnailsClick = function(e) {
                       e = e || window.event;
                       e.preventDefault ? e.preventDefault() : e.returnValue = false;

                       var eTarget = e.target || e.srcElement;
                       var clickedListItem = closest(eTarget, function(el) {
                       return (el.tagName && el.tagName.toUpperCase() === 'FIGURE');
                       });

                       if(!clickedListItem) {
                       return;
                       }

                       var clickedGallery = clickedListItem.parentNode,
                       childNodes = clickedListItem.parentNode.childNodes,
                       numChildNodes = childNodes.length,
                       nodeIndex = 0,
                       index;

                       for (var i = 0; i < numChildNodes; i++) {
                       if(childNodes[i].nodeType !== 1) {
                       continue;
                       }

                       if(childNodes[i] === clickedListItem) {
                       index = nodeIndex;
                       break;
                       }
                       nodeIndex++;
                       }

                       if(index >= 0) {

                       openPhotoSwipe( index, clickedGallery );
                       }
                       return false;
                       };
                       var photoswipeParseHash = function() {
                       var hash = window.location.hash.substring(1),
                       params = {};

                       if(hash.length < 5) {
                       return params;
                       }

                       var vars = hash.split('&');
                       for (var i = 0; i < vars.length; i++) {
                       if(!vars[i]) {
                       continue;
                       }
                       var pair = vars[i].split('=');
                       if(pair.length < 2) {
                       continue;
                       }
                       params[pair[0]] = pair[1];
                       }

                       if(params.gid) {
                       params.gid = parseInt(params.gid, 10);
                       }

                       if(!params.hasOwnProperty('pid')) {
                       return params;
                       }
                       params.pid = parseInt(params.pid, 10);
                       return params;
                       };

                       var openPhotoSwipe = function(index, galleryElement, disableAnimation) {
                       var pswpElement = document.querySelectorAll('.pswp')[0],
                       gallery,
                       options,
                       items;

                       items = parseThumbnailElements(galleryElement);
                       options = {
                       index: index,
                       galleryUID: galleryElement.getAttribute('data-pswp-uid'),

                       getThumbBoundsFn: function(index) {

                       var thumbnail = items[index].el.getElementsByTagName('img')[0],
                       pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
                       rect = thumbnail.getBoundingClientRect();

                       return {x:rect.left, y:rect.top + pageYScroll, w:rect.width};
                       }

                       };

                       if(disableAnimation) {
                       options.showAnimationDuration = 0;
                       }
                       gallery = new PhotoSwipe( pswpElement, PhotoSwipeUI_Default, items, options);
                       gallery.init();
                       };
                       var galleryElements = document.querySelectorAll( gallerySelector );

                       for(var i = 0, l = galleryElements.length; i < l; i++) {
                       galleryElements[i].setAttribute('data-pswp-uid', i+1);
                       galleryElements[i].onclick = onThumbnailsClick;
                       }
                       var hashData = photoswipeParseHash();
                       if(hashData.pid > 0 && hashData.gid > 0) {
                       openPhotoSwipe( hashData.pid - 1 ,  galleryElements[ hashData.gid - 1 ], true );
                       }
                       };
                       initPhotoSwipeFromDOM( '.my-gallery' ) ;"))
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div#photo
               [:h1 "Photo"]
               [:div.my-gallery
                [:div.raw
                 (map #(vec [:div.col-xs-6.col-md-3 {:key (:photo/id %)}
                             [:figure
                              [:a {:href (:photo/href %) :data-size "800x600"}
                               [:img {:src (:photo/href %) :alt (:photo/caption %)}]]
                              [:figcaption (:photo/caption %)]]])
                      [{:photo/id 1 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}
                       {:photo/id 2 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}
                       {:photo/id 3 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}
                       {:photo/id 4 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}
                       {:photo/id 5 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}
                       {:photo/id 6 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}
                       {:photo/id 7 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}
                       {:photo/id 8 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}
                       {:photo/id 9 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}
                       {:photo/id 10 :photo/caption "nagou" :photo/href "/img/member/nagou.jpg"}])]]]))))
