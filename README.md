# 名古屋大学相撲部ホームページ

## 構成

* om-next
* AWS 上で動かすために、 frontend を S3, backend を Lambda + API Gateway で動かす

## AWS 構成

| Service             | 備考 |
| ------------------- | ------------ |
| S3                  | フロントエンドホスティング |
| Route53             |  |
| Certificate Manager |  |
| CoundFront          | フロントエンド SSL 対応用 |
| API Gateway         | バックエンドホスティング (Lambda 呼び出し) |
| Lambda              | ring-aws-lambda-adapter により ring handler を呼び出す |
| SES                 | 問い合わせ処理用 |
| DynamoDB            | 問い合わせ処理用 |

## Quick start

Assuming Cider+emacs development environment:

1. After cloneing/downloading. Open _meidai-sumo/src/cljs/ui/core.cljs_
2. In the clj-repl type `(load-file "script/figwheel.clj")` to start figwheel
3. Open http://localhost:8440
4. Add css/scss styling in  _meidai-sumo/scss/style.scss_ (must have scss installed, do so with `sudo npm install -g node-sass`, read more out the SCSS figwheel script here https://github.com/bhauman/lein-figwheel/wiki/SASS-watcher)

## Memo

### AWS 上での動作確認

```
$ curl -XPOST localhost:8440/api -H "Content-Type: application/transit+json" -d '["~:app/member"']'
$ curl -XPOST https://api.meidai-sumo.club  -H "Content-Type: application/transit+json" -d '["~:app/member"]'
```

### CloudFront キャッシュ削除

1. CloudFront の [Invalidation] タブから [Create Invalidation] ボタン
2. "/*" を指定して [Invalidate]

