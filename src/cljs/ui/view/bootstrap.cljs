(ns ui.view.bootstrap
  (:require [cljsjs.react-bootstrap]))

(defn navbar [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar") (clj->js props) children))
(defn navbarHeader [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar" "Header") (clj->js props) children))
(defn navbarBrand [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar" "Brand") (clj->js props) children))
(defn navbarCollapse [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar" "Collapse") (clj->js props) children))
(defn navbarToggle [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar" "Toggle") (clj->js props) children))
(defn nav [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Nav") (clj->js props) children))
(defn navItem [props & children] (apply js/React.createElement (aget js/ReactBootstrap "NavItem") (clj->js props) children))
(defn navDropdown [props & children] (apply js/React.createElement (aget js/ReactBootstrap "NavDropdown") (clj->js props) children))
(defn menuItem [props & children] (apply js/React.createElement (aget js/ReactBootstrap "MenuItem") (clj->js props) children))
(defn tabs [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Tabs") (clj->js props) children))
(defn tab [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Tab") (clj->js props) children))
