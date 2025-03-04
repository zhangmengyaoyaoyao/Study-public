建立一棵二叉树，并输出前序、中序、后序遍历结果。

例如，平台会执行如下代码，对函数进行测试：

```java
BinTree root = new BinTree();
BinTree.CreateBT("ABDCEGFHI", "DBAEGCHFI", root);
       assertTrue("ABDCEGFHI".equals(BinTree.PreOrderTraversal(root).toString()));
assertTrue("DBAEGCHFI".equals(BinTree.InOrderTraversal(root).toString()));
assertTrue("DBGEHIFCA".equals(BinTree.PostOrderTraversal(root).toString()));
```

