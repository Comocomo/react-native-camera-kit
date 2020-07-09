import * as _ from 'lodash'
import PropTypes from 'prop-types'
import React, {Component} from 'react'
import {
  requireNativeComponent,
  NativeModules,
  processColor,
  NativeEventEmitter
} from 'react-native';

const NativeCamera = requireNativeComponent('CKCamera', null);
const NativeCameraAction = NativeModules.CKCameraManager;
const iosErrorEventEmitter = new NativeEventEmitter(NativeModules.ErrorEventEmitter);
var subscription = null

export default class CameraKitCamera extends React.Component {
  static propTypes = {
    onErrorIOS: PropTypes.func,
  }

  static defaultProps = {
    onErrorIOS: () => {}
  }
  constructor(props){
    super(props)
    subscription = iosErrorEventEmitter.addListener(
      'ErrorEventEmitter/error',
      this.onErrorIOS.bind(this)
    )
  }

  onErrorIOS(error) {
    this.props.onIOSError(error)
  }


  componentWillUnmount(){
    subscription.remove()
  }

  render() {
    const transformedProps = _.cloneDeep(this.props);
    _.update(transformedProps, 'cameraOptions.ratioOverlayColor', (c) => processColor(c));
    return <NativeCamera {...transformedProps}/>
  }

  static async checkDeviceCameraAuthorizationStatus() {
    return await NativeCameraAction.checkDeviceCameraAuthorizationStatus();

  }

  static async requestDeviceCameraAuthorization() {
    return await NativeCameraAction.requestDeviceCameraAuthorization();
  }

  async capture(saveToCameraRoll = true) {
    return await NativeCameraAction.capture(saveToCameraRoll);
  }

  registerBarcodeReader() {
    NativeCameraAction.registerBarcodeReader();
  }

  async changeCamera() {
    return await NativeCameraAction.changeCamera();
  }

  async setFlashMode(flashMode = 'auto') {
    return await NativeCameraAction.setFlashMode(flashMode);
  }

  async setTorchMode(torchMode = '') {
    return await NativeCameraAction.setTorchMode(torchMode);
  }
}
